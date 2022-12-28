package com.graceannafitrisinaga.weatherprediction.retrofit.weatherModel

data class Hourly(
    val clouds: Long,
    val dew_poLong: Double,
    val dt: Long,
    val feels_like: Double,
    val humidity: Long,
    val pop: Double,
    val pressure: Long,
    val temp: Double,
    val uvi: Double,
    val visibility: Long,
    val weather: List<Weather>,
    val wind_deg: Long,
    val wind_gust: Double,
    val wind_speed: Double
)