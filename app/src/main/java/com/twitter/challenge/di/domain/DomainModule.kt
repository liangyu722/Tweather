package com.twitter.challenge.di.domain

import com.twitter.challenge.data.WeatherRepository
import com.twitter.challenge.domain.ViewWeather
import com.twitter.challenge.domain.ViewWeatherUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun providesViewWeatherUseCase(repository: WeatherRepository): ViewWeather {
        return ViewWeatherUseCase(repository)
    }
}
