package com.twitter.challenge.data.networking.response

data class WeatherResponse(
        val clouds: Clouds,
        val name: String,
        val weather: Weather,
        val wind: Wind
)