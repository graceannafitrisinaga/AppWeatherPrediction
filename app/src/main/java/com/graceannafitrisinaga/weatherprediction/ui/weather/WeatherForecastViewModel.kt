package com.graceannafitrisinaga.weatherprediction.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.graceannafitrisinaga.weatherprediction.repo.WeatherRepository
import com.graceannafitrisinaga.weatherprediction.retrofit.weatherModel.WeatherResponse

class WeatherForecastViewModel : ViewModel() {
    //menginisiasi variabel repository untuk menggunakan class WeatherRepository
    private val repository : WeatherRepository = WeatherRepository()

    //fungsi untuk mengambil data weather menggunakan live data yang ada pada class WeatherResponse
    fun getWeatherData(lat: Double, lon: Double): LiveData<WeatherResponse> {
        repository.getWeatherData(lat, lon)
        return repository.weatherData
    }
}
