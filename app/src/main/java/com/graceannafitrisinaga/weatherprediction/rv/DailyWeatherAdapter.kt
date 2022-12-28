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
import com.graceannafitrisinaga.weatherprediction.retrofit.weatherModel.Daily
import com.graceannafitrisinaga.weatherprediction.utils.DateUtils
import java.util.*
import kotlin.collections.ArrayList

class DailyWeatherAdapter(private val context: Context) : RecyclerView.Adapter<DailyWeatherAdapter.DailyWeatherViewModel>() {

    val allDailyWeatherItem : ArrayList<Daily> = ArrayList()

    inner class DailyWeatherViewModel(itemView: View) : RecyclerView.ViewHolder(itemView){
        val dateHeading : TextView = itemView.findViewById(R.id.dailyWeatherDate)
        val minTemp : TextView = itemView.findViewById(R.id.dailyWeatherMinTemp)
        val maxTemp : TextView = itemView.findViewById(R.id.dailyWeatherMaxTemp)
        val humidity : TextView = itemView.findViewById(R.id.dailyWeatherHumidity)
        val skyType : TextView = itemView.findViewById(R.id.dailyWeatherSkyType)
        val sunRiseTime : TextView = itemView.findViewById(R.id.dailyWeatherSunRiseTime)
        val sunSetTime : TextView = itemView.findViewById(R.id.dailyWeatherSunSetTime)

        val icon : ImageView = itemView.findViewById(R.id.dailyWeatherIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewModel {
        return DailyWeatherViewModel(LayoutInflater.from(parent.context).inflate(R.layout.daily_weather_item, parent, false))
    }

    override fun onBindViewHolder(holder: DailyWeatherViewModel, position: Int) {
        val currentDay = allDailyWeatherItem[position]

        Glide.with(context).load("https://openweathermap.org/img/w/${currentDay.weather[0].icon}.png").into(holder.icon)

        holder.dateHeading.text = DateUtils.getDateTime(currentDay.dt, "EEEE dd-MM-YYYY")
        holder.minTemp.text = "${(currentDay.temp.min - 273).toInt()}"
        holder.maxTemp.text = "${(currentDay.temp.max - 273).toInt()}"
        holder.humidity.text = "${currentDay.humidity}%"
        holder.sunRiseTime.text = DateUtils.getDateTime(currentDay.sunrise, "hh:MM a")
        holder.sunSetTime.text = DateUtils.getDateTime(currentDay.sunset, "hh:MM a")
        holder.skyType.text = currentDay.weather[0].description.trim().split("\\s+".toRegex())
            .joinToString(" ") { it ->
                it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() } }
    }

    override fun getItemCount(): Int {
        return allDailyWeatherItem.size
    }

    fun updateALlDailyWeatherItems(list : List<Daily>){
        allDailyWeatherItem.clear()
        allDailyWeatherItem.addAll(list)
        this.notifyDataSetChanged()
    }
}