package com.graceannafitrisinaga.weatherprediction.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graceannafitrisinaga.weatherprediction.R
import com.graceannafitrisinaga.weatherprediction.retrofit.weatherModel.Hourly
import com.graceannafitrisinaga.weatherprediction.utils.DateUtils

class HourlyWeatherAdapter(private val context: Context) : RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherViewHolder>() {

    private val allHourlyWeatherItems = ArrayList<Hourly>()

    inner class HourlyWeatherViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val timeHour : TextView = itemView.findViewById(R.id.hourlyWeatherTimeTextView)
        val temp : TextView = itemView.findViewById(R.id.hourlyWeatherTempTextView)
        val humidity : TextView = itemView.findViewById(R.id.hourlyWeatherHumidityTextView)
        val icon : ImageView = itemView.findViewById(R.id.hourlyWeatherIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {
        return HourlyWeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hourly_weather_item, parent, false))
    }

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        val currentHourlyItem = allHourlyWeatherItems[position]

        Glide.with(context).load("https://openweathermap.org/img/w/${currentHourlyItem.weather[0].icon}.png").into(holder.icon)
        holder.timeHour.text = "${DateUtils.getDateTime(currentHourlyItem.dt, "hh")?.toInt().toString()} ${DateUtils.getDateTime(currentHourlyItem.dt, "a")}"
        holder.temp.text = "${(currentHourlyItem.temp-273).toInt()}°"
        holder.humidity.text = "${currentHourlyItem.humidity} %"
    }

    override fun getItemCount(): Int {
        return allHourlyWeatherItems.size
    }

    fun updateALlHourlyItems(list : List<Hourly>){
        allHourlyWeatherItems.clear()
        allHourlyWeatherItems.addAll(list)
        this.notifyDataSetChanged()
    }
}