package com.twitter.challenge.data

data class WeatherEntity(
        val locationName: String,
        val tempCesius: Double,
        val windSpeed: Double,
        val cloudiness: Int
)