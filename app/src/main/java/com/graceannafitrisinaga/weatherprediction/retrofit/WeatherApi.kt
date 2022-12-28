package com.graceannafitrisinaga.weatherprediction.retrofit

import com.graceannafitrisinaga.weatherprediction.retrofit.weatherModel.WeatherResponse
import com.graceannafitrisinaga.weatherprediction.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    //mengambil WeatherResponse dengan satu kali panggilan
    @GET("onecall")
    fun getWeatherData(@Query("lat") latitude : Double,
                       @Query("lon") longitude : Double,
                       @Query("exclude") exclude : String,
                       @Query("appid") apiKey : String = Constants.apiKey) : Call<WeatherResponse>
}