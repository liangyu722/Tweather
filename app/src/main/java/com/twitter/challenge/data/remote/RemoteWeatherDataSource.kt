package com.twitter.challenge.data.remote

import com.twitter.challenge.common.Result
import com.twitter.challenge.data.WeatherDataSource
import com.twitter.challenge.data.WeatherEntity
import com.twitter.challenge.data.remote.network.response.WeatherResponse
import com.twitter.challenge.data.remote.network.WeatherService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.awaitAll

class RemoteWeatherDataSource(
        private val weatherService: WeatherService
) : WeatherDataSource {

    override suspend fun getCurrentWeatherEntry(): Result<WeatherEntity> {
        return try {
            val weatherResponse = weatherService.currentWeatherAsync().await()
            Result.Success(weatherResponse.toWeatherEntity())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getFutureWeatherEntry(days: Int): Result<List<WeatherEntity>> {
        return fetchFutureWeatherFromRemote(days)
    }

    private suspend fun fetchFutureWeatherFromRemote(days: Int): Result<List<WeatherEntity>> {
        return try {
            val deferredFutureWeatherList: List<Deferred<WeatherResponse>> = (1..days)
                    .map { dayInAdvance ->
                        weatherService.futureWeatherAsync(dayInAdvance)
                    }
            val futureWeatherEntityList = deferredFutureWeatherList.awaitAll().map {
                it.toWeatherEntity()
            }
            Result.Success(futureWeatherEntityList)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private fun WeatherResponse.toWeatherEntity() = WeatherEntity(
            locationName = this.name,
            tempCelsius = this.weather.temp,
            cloudiness = this.clouds.cloudiness,
            windSpeed = this.wind.speed
    )
}
