package com.twitter.challenge.data

import com.twitter.challenge.common.DataLoadingException
import com.twitter.challenge.common.Result
import com.twitter.challenge.data.cache.WeatherCache
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultWeatherRepository(
        private val remoteWeatherDataSource: WeatherDataSource,
        private val weatherCache: WeatherCache,
        private val backgroundDispatcher: CoroutineDispatcher
) : WeatherRepository {

    override suspend fun getCurrentWeatherEntry(): Result<WeatherEntity> {
        return withContext(backgroundDispatcher) {
            weatherCache.getCurrentWeather()?.let { cachedCurrentWeather ->
                return@withContext Result.Success(cachedCurrentWeather)
            }

            val newCurrentWeather = remoteWeatherDataSource.getCurrentWeatherEntry()
            (newCurrentWeather as? Result.Success)?.let {
                weatherCache.setCurrentWeather(it.data)
                return@withContext newCurrentWeather
            }

            return@withContext Result.Error(DataLoadingException("Cannot load current weather"))
        }
    }

    override suspend fun getFutureWeatherEntry(days: Int): Result<List<WeatherEntity>> {
        return withContext(backgroundDispatcher) {
            val cachedFutureWeather = weatherCache.getFutureWeather()
            if (cachedFutureWeather.isNotEmpty()) {
                return@withContext Result.Success(cachedFutureWeather)
            }

            val newFutureWeather = remoteWeatherDataSource.getFutureWeatherEntry(days)
            (newFutureWeather as? Result.Success)?.let {
                weatherCache.setFutureWeather(it.data)
                return@withContext newFutureWeather
            }
            return@withContext Result.Error(DataLoadingException("Cannot load future weather"))
        }
    }

}
