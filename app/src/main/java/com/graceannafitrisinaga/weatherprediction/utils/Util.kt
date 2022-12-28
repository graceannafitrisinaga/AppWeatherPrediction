package com.graceannafitrisinaga.weatherprediction.utils

class Util {
    companion object{
        fun getCardinalDirection(angle: Long) : String {
            val directions = listOf<String>("N",
                "NE", "E", "SE", "S", "SW", "W", "NW"
            )
            return directions[((angle / 45) % 8).toInt()]
        }
    }
}