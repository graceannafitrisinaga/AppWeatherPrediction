package com.graceannafitrisinaga.weatherprediction.retrofit.weatherModel

data class Current(
    val clouds: Long,
    val dew_poLong: Double,
    val dt: Long,
    val feels_like: Double,
    val humidity: Long,
    val pressure: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    val uvi: Double,
    val visibility: Long,
    val weather: List<Weather>,
    val wind_deg: Long,
    val wind_gust: Double,
    val wind_speed: Double
)