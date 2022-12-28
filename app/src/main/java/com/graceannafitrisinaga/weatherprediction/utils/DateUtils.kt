package com.graceannafitrisinaga.weatherprediction.utils

import java.security.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object{
        fun getDateTime(timeStamp: Long, pattern: String): String? {
            return SimpleDateFormat(pattern, Locale.ENGLISH).format(timeStamp * 1000)
        }
    }
}