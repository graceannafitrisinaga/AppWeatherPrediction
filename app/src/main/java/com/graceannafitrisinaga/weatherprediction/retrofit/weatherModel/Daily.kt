package com.graceannafitrisinaga.weatherprediction.retrofit.weatherModel

data class Daily(
    val clouds: Long,
    val dew_poLong: Double,
    val dt: Long,
    val feels_like: FeelsLike,
    val humidity: Long,
    val moon_phase: Double,
    val moonrise: Long,
    val moonset: Long,
    val pop: Double,
    val pressure: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Temp,
    val uvi: Double,
    val weather: List<Weather>,
    val wind_deg: Long,
    val wind_gust: Double,
    val wind_speed: Double
)