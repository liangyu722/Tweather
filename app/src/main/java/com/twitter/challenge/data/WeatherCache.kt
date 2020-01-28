package com.twitter.challenge.data

interface WeatherCache {

    suspend fun setCurrentWeather(currentWeather: WeatherEntity)

    suspend fun getCurrentWeather(): WeatherEntity?

    suspend fun setFutureWeather(futureWeathers: List<WeatherEntity>)

    suspend fun getFutureWeather(): List<WeatherEntity>
}
