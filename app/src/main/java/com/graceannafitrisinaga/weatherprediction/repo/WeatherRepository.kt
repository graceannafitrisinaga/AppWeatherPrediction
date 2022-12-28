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
    //variabel untuk mengkases class WeatherApi
    private val weatherApi =
        NetworkUtils.getWeatherRetrofitInstance().create(WeatherApi::class.java)
    //variabel untuk mengambil data weather pada file WeatherResponse
    private val _weatherData: MutableLiveData<WeatherResponse> = MutableLiveData()

    //mengambil data menggunakan LiveData dari file WeatherResponse
    val weatherData: LiveData<WeatherResponse> = _weatherData

    //fungsi untuk menampilkan data yang diambil dari webservice
    fun getWeatherData(lat: Double, lon: Double) {
        weatherApi.getWeatherData(lat, lon, "minutely")
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    //jika response berhasil maka data akan ditampilkan
                    if (response.isSuccessful) {
                        _weatherData.postValue(response.body())
                        println("response is ${response.body()}")
                    }
                }
                //jika response tidak berhasil
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.d("WeatherApi", "Failed to fetch data ${t.cause}")
                }

            })
    }
}