package com.twitter.challenge.di.repository

import com.twitter.challenge.data.DefaultWeatherRepository
import com.twitter.challenge.data.WeatherDataSource
import com.twitter.challenge.data.WeatherRepository
import com.twitter.challenge.data.cache.WeatherCache
import com.twitter.challenge.data.cache.WeatherMemoryCache
import com.twitter.challenge.data.remote.RemoteWeatherDataSource
import com.twitter.challenge.data.remote.network.WeatherService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun providesDataSource(weatherService: WeatherService): WeatherDataSource {
        return RemoteWeatherDataSource(weatherService)
    }

    @Singleton
    @Provides
    fun provdesWeatherCache(): WeatherCache {
        return WeatherMemoryCache()
    }

    @Singleton
    @Provides
    fun providesWeatherRepository(
            weatherDataSource: WeatherDataSource,
            weatherCache: WeatherCache
    ): WeatherRepository {
        return DefaultWeatherRepository(weatherDataSource, weatherCache, Dispatchers.IO)
    }
}
