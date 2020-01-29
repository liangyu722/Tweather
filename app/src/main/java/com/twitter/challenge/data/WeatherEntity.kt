package com.twitter.challenge.data

data class WeatherEntity(
        val locationName: String,
        val tempCelsius: Double,
        val windSpeed: Double,
        val cloudiness: Int
)