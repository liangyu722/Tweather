package com.twitter.challenge.data.remote.network.response

data class WeatherResponse(
        val clouds: Clouds,
        val name: String,
        val weather: Weather,
        val wind: Wind
)
