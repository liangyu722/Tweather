package com.twitter.challenge.data

import com.twitter.challenge.common.Result

interface WeatherRepository {

    suspend fun getCurrentWeatherEntry(): Result<WeatherEntity>

    suspend fun getFutureWeatherEntry(days: Int): Result<List<WeatherEntity>>

}
