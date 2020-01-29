package com.twitter.challenge.model

data class Weather (
    val locationName: String,
    val tempCelsius: Double,
    val tempFahrenheit: Double,
    val windSpeed: Double,
    val cloudiness: Int
)