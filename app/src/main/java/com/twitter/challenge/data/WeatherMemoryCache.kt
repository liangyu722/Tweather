package com.twitter.challenge.data

import com.twitter.challenge.data.WeatherCache
import com.twitter.challenge.data.WeatherEntity

class WeatherMemoryCache : WeatherCache {

    private var currentWeatherData: WeatherEntity? = null
    private var futureWeathersData: List<WeatherEntity> = emptyList()

    override suspend fun setCurrentWeather(currentWeather: WeatherEntity) {
        currentWeatherData = currentWeather
    }

    override suspend fun getCurrentWeather() = currentWeatherData

    override suspend fun setFutureWeather(futureWeathers: List<WeatherEntity>) {
        futureWeathersData = futureWeathers
    }

    override suspend fun getFutureWeather(): List<WeatherEntity> = futureWeathersData
}
