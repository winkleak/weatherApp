package com.home.wink.weatherapp.domain.entity


enum class WindDirection(val value: String) {
    NORD("nord"), EAST("east"), SOUTH("south"), WEST("west"), UNDEFINED("undefined");

    companion object {
        fun directionByDegree(degree: Double): WindDirection {
            return if (degree in 315.1..360.0 || degree in 0.0..44.9) {
                NORD
            } else if (degree in 45.0..134.9) {
                EAST
            } else if (degree in 135.0..224.9) {
                SOUTH
            } else if (degree in 225.0..315.0) WEST else UNDEFINED
        }
    }
}





