package com.twitter.challenge.model

data class Weather (
    val locationName: String,
    val tempCelsius: Float,
    val tempFahrenheit: Float,
    val windSpeed: Double,
    val cloudiness: Int
)