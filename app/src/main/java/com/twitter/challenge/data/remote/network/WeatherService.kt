package com.twitter.challenge.data.remote.network

import com.twitter.challenge.data.networking.response.WeatherResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("current.json")
    fun currentWeatherAsync(): Deferred<WeatherResponse>

    @GET("future_{day_in_advance}.json")
    fun futureWeatherAsync(
            @Path(value = "day_in_advance", encoded = true) dayInAdvance: Int
    ): Deferred<WeatherResponse>

}