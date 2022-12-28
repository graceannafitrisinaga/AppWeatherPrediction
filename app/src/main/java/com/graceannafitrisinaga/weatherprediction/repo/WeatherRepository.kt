package com.graceannafitrisinaga.weatherprediction.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.graceannafitrisinaga.weatherprediction.retrofit.WeatherApi
import com.graceannafitrisinaga.weatherprediction.retrofit.weatherModel.WeatherResponse
import com.graceannafitrisinaga.weatherprediction.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherRepository {

    private val weatherApi =
        NetworkUtils.getWeatherRetrofitInstance().create(WeatherApi::class.java)


    private val _weatherData: MutableLiveData<WeatherResponse> = MutableLiveData()

    val weatherData: LiveData<WeatherResponse> = _weatherData

    fun getWeatherData(lat: Double, lon: Double) {
        weatherApi.getWeatherData(lat, lon, "minutely")
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful) {
                        _weatherData.postValue(response.body())
                        println("response is ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.d("WeatherApi", "Failed to fetch data ${t.cause}")
                }

            })
    }
}