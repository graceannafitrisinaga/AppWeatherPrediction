package com.graceannafitrisinaga.weatherprediction.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.graceannafitrisinaga.weatherprediction.repo.WeatherRepository
import com.graceannafitrisinaga.weatherprediction.retrofit.weatherModel.WeatherResponse

class WeatherForecastViewModel : ViewModel() {
    private val repository : WeatherRepository = WeatherRepository()

    fun getWeatherData(lat: Double, lon: Double): LiveData<WeatherResponse> {
        repository.getWeatherData(lat, lon)
        return repository.weatherData
    }
}
