package com.twitter.challenge.domain

import com.twitter.challenge.common.Result
import com.twitter.challenge.model.TempStandDeviation
import com.twitter.challenge.model.Weather

interface ViewWeather {

    suspend fun getCurrentWeather(): Result<Weather>

    suspend fun getFutureWeatherStandardDeviation(days: Int): Result<TempStandDeviation>

}
