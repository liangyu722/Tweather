package com.twitter.challenge.data.cache

import com.twitter.challenge.data.WeatherEntity

interface WeatherCache {

    suspend fun setCurrentWeather(currentWeather: WeatherEntity)

    suspend fun getCurrentWeather(): WeatherEntity?

    suspend fun setFutureWeather(futureWeathers: List<WeatherEntity>)

    suspend fun getFutureWeather(): List<WeatherEntity>
}
