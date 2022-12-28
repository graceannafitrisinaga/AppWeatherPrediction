package com.graceannafitrisinaga.weatherprediction.ui.weather

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.graceannafitrisinaga.weatherprediction.databinding.FragmentWeatherForecastBinding
import com.graceannafitrisinaga.weatherprediction.rv.DailyWeatherAdapter
import com.graceannafitrisinaga.weatherprediction.rv.HourlyWeatherAdapter
import com.graceannafitrisinaga.weatherprediction.utils.DateUtils
import com.graceannafitrisinaga.weatherprediction.utils.NetworkUtils
import com.graceannafitrisinaga.weatherprediction.utils.Util.Companion.getCardinalDirection
import com.google.android.gms.location.*
import kotlin.math.roundToInt


class WeatherForecastFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherForecastFragment()
    }

    private lateinit var binding: FragmentWeatherForecastBinding
    private lateinit var viewModel: WeatherForecastViewModel
    private lateinit var hourlyAdapter: HourlyWeatherAdapter
    private lateinit var dailyAdapter: DailyWeatherAdapter
    private lateinit var progress : ProgressDialog

    private val requestCode = 736

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherForecastBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[WeatherForecastViewModel::class.java]


        // menginisiasi progress
        progress = ProgressDialog(this.requireContext())
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false) // disable dismiss by tapping outside of the dialog


        // menginisiasi lokasi cuaca
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.requireContext())


        if (!NetworkUtils.isConnectedToInternet(this.requireContext())){

            if (!isLocationEnabled(requireContext())){
                // notify user
                requestLocation()
                binding.locationNotEnabledWarning.visibility = View.VISIBLE
                binding.reloadButton.visibility = View.VISIBLE
            }

            binding.internetNotEnabledWarning.visibility = View.VISIBLE
            binding.reloadButton.visibility = View.VISIBLE
            binding.currentWeatherHead.root.visibility = View.GONE
            binding.hourlyWeatherLabel.visibility = View.GONE
            binding.dailyWeatherLabel.visibility = View.GONE
        }else{

            if (isLocationEnabled(requireContext())){
                //mengambil lokasi cuaca terbaru
                getLocationWeather()

            }else{
                requestLocation()
                binding.locationNotEnabledWarning.visibility = View.VISIBLE
                binding.reloadButton.visibility = View.VISIBLE
            }
        }


        // mereload data
        binding.reloadButton.setOnClickListener {
            if (NetworkUtils.isConnectedToInternet(this.requireContext()) && isLocationEnabled(requireContext())){

//                progress.show()
//                getCurrentLocationWeather()
                getLocationWeather()

            }else{
                if (!isLocationEnabled(requireContext())){
                    // notifikasi pengguna
                    requestLocation()
                    binding.locationNotEnabledWarning.visibility = View.VISIBLE
                }else{
                    binding.locationNotEnabledWarning.visibility = View.GONE
                }

                if (!NetworkUtils.isConnectedToInternet(this.requireContext())){

                    binding.internetNotEnabledWarning.visibility = View.VISIBLE
                    Toast.makeText(this.requireContext(), "First connect with internet", Toast.LENGTH_SHORT).show()
                }
            }
        }



        // menginisiasi cuaca per jam
        hourlyAdapter = HourlyWeatherAdapter(this.requireContext())
        binding.HourlyWeatherRecyclerView.layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.HourlyWeatherRecyclerView.adapter = hourlyAdapter


        // menginisiasi cuaca perhari
        dailyAdapter = DailyWeatherAdapter(this.requireContext())
        binding.dailyWeatherRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        binding.dailyWeatherRecyclerView.adapter = dailyAdapter

        isLocationPermissionGranted()


        return binding.root
    }

    private fun requestLocation() {
        if (isLocationPermissionGranted()){
            AlertDialog.Builder(context)
                .setMessage("GPS not enabled")
                .setPositiveButton("Enable location",
                    DialogInterface.OnClickListener { paramDialogInterface, paramInt ->
                        requireContext().startActivity(
                            Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        )
                    })
                .setNegativeButton("Cancel", null)
                .show()
        }
    }


    private fun getWeatherData(lat : Double, lon : Double) {
        if (lat != -1.0 && lon != -1.0) {


            viewModel.getWeatherData(lat, lon).observe(viewLifecycleOwner, Observer {
                if (it != null) {

                    // Show layout
                    binding.internetNotEnabledWarning.visibility = View.GONE
                    binding.locationNotEnabledWarning.visibility = View.GONE
                    binding.reloadButton.visibility = View.GONE
                    binding.currentWeatherHead.root.visibility = View.VISIBLE
                    binding.hourlyWeatherLabel.visibility = View.VISIBLE
                    binding.dailyWeatherLabel.visibility = View.VISIBLE

                    // Dismiss the progress dialog
                    progress.dismiss()

                    // Current Weather
                    binding.currentWeatherHead.weatherDateHeading.text =
                        DateUtils.getDateTime(it.current.dt, "EEEE dd-MM-YYYY HH:MM:SS")

                    Glide.with(this.requireContext())
                        .load("https://openweathermap.org/img/w/${it.current.weather[0].icon}.png")
                        .into(binding.currentWeatherHead.currentWeatherIcon)

                    binding.currentWeatherHead.weatherCurrentTemp.text =
                        "${(it.current.temp - 273).roundToInt()}°"
                    binding.currentWeatherHead.currentWeatherTimeZone.text = it.timezone

                    val pop = if (it.hourly[0].pop == 0.0) {
                        0
                    } else {
                        it.hourly[0].pop
                    }

                    binding.currentWeatherHead.currentWeatherPrecipitation.text = "$pop mm"
                    binding.currentWeatherHead.currentWeatherHumidity.text =
                        "${it.current.humidity} %"
                    binding.currentWeatherHead.currentWeatherWindSpeed.text =
                        "${it.current.wind_speed}kmph"
                    binding.currentWeatherHead.currentWeatherWindDirection.text =
                        getCardinalDirection(it.current.wind_deg)

                    // Hourly Weather
                    hourlyAdapter.updateALlHourlyItems(it.hourly)

                    // Daily Weather
                    dailyAdapter.updateALlDailyWeatherItems(it.daily)

                }
            })
        }
    }


    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this.requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this.requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this.requireActivity(),
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                requestCode
            )
            false
        } else {
            true
        }
    }


    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()

        if (isLocationEnabled(requireContext()) && NetworkUtils.isConnectedToInternet(this.requireContext())){
            binding.locationNotEnabledWarning.visibility = View.GONE
            binding.internetNotEnabledWarning.visibility = View.GONE
            binding.reloadButton.visibility = View.GONE
//            getCurrentLocationWeather()
            getLocationWeather()

        }else if (isLocationEnabled(requireContext())){
            binding.locationNotEnabledWarning.visibility = View.GONE
        }else if (NetworkUtils.isConnectedToInternet(this.requireContext())){
            NetworkUtils.isConnectedToInternet(this.requireContext())
        }
    }

    private fun isLocationEnabled(context: Context): Boolean {
        return if (isLocationPermissionGranted()){
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            LocationManagerCompat.isLocationEnabled(locationManager)
        }else{
            false
        }
    }


   /* @SuppressLint("MissingPermission")
    fun getCurrentLocationWeather(){
        if (isLocationEnabled(requireContext())){

            Log.d("current location", "location enabled")
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    // Got last known location. In some rare situations this can be null.

                    location?.let {

                        getWeatherData(it.latitude, it.longitude)

                        Log.d("current location", "${it.latitude}, ${it.longitude}")
                    }
                }
        }
        else
        {
            requestLocation()
            Log.d("current location", "location not enabled")
        }
    }
*/


    @SuppressLint("MissingPermission")
    fun getLocationWeather(){

        Log.d("current location", "get location: called")
        progress.setTitle("Fetching")
        progress.setMessage("Fetching location data...")

        // Showing Progress Dialog
        if (!progress.isShowing){
            progress.dismiss()
        }
        progress.show()

        val mLocationRequest: LocationRequest = LocationRequest.create()
        mLocationRequest.numUpdates = 1
        mLocationRequest.interval = 4000
        mLocationRequest.fastestInterval = 1000
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val mLocationCallback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {

                locationResult.let {

                    for (location in it.locations) {
                        location?.let {
                            getWeatherData(it.latitude, it.longitude)
                            Log.d("current location", "get location: ${it.latitude}, ${it.longitude}")
                        }
                    }
                }

            }
        }
        LocationServices.getFusedLocationProviderClient(requireContext())
            .requestLocationUpdates(mLocationRequest, mLocationCallback, null)
    }

}