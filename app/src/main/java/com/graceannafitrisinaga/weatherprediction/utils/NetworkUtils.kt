package com.graceannafitrisinaga.weatherprediction.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {

    companion object{
        //getting retrofit instance
        fun getRetrofitInstance(): Retrofit {
            val client = Retrofit.Builder()
            client.baseUrl("https://reqres.in/")
            client.addConverterFactory(GsonConverterFactory.create())
            return client.build()
        }

        fun getWeatherRetrofitInstance(): Retrofit {
            val client = Retrofit.Builder()
            client.baseUrl("https://api.openweathermap.org/data/2.5/")
            client.addConverterFactory(GsonConverterFactory.create())
            return client.build()
        }

        fun isConnectedToInternet(context: Context): Boolean {
            val connectivity = context.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            val info = connectivity.allNetworkInfo
            for (i in info.indices)
                if (info[i].state == NetworkInfo.State.CONNECTED) return true

            return false
        }

    }

}