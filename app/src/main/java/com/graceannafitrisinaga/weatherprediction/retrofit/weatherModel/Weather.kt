package com.graceannafitrisinaga.weatherprediction.retrofit.weatherModel

data class Weather(
    val description: String,
    val icon: String,
    val id: Long,
    val main: String
)