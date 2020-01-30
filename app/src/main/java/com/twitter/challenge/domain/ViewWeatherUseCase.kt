package com.twitter.challenge.domain

import com.twitter.challenge.common.InvalidCalculationException
import com.twitter.challenge.common.Result
import com.twitter.challenge.common.Result.Error
import com.twitter.challenge.common.Result.Success
import com.twitter.challenge.data.WeatherEntity
import com.twitter.challenge.data.WeatherRepository
import com.twitter.challenge.model.TempStandDeviation
import com.twitter.challenge.model.Weather
import kotlin.math.pow
import kotlin.math.sqrt

class ViewWeatherUseCase(
        private val repository: WeatherRepository
) : ViewWeather {

    override suspend fun getCurrentWeather(): Result<Weather> {
        return when (val currentWeatherResult = repository.getCurrentWeatherEntry()) {
            is Success -> Success(currentWeatherResult.data.toWeather())
            is Error -> currentWeatherResult
        }
    }

    override suspend fun getFutureWeatherStandardDeviation(days: Int): Result<TempStandDeviation> {
        return when (val futureWeatherResponse = repository.getFutureWeatherEntry(days)) {
            is Success -> {
                try {
                    val standDeviationCelsius = futureWeatherResponse.data.map {
                        it.tempCelsius
                    }.calculateStandardDeviation()

                    val standDeviationFahrenheit = futureWeatherResponse.data.map {
                        celsiusToFahrenheit(it.tempCelsius)
                    }.calculateStandardDeviation()

                    Success(TempStandDeviation(
                            days = days,
                            standDeviationCelsius = standDeviationCelsius,
                            standDeviationFahrenheit = standDeviationFahrenheit)
                    )
                } catch (e: InvalidCalculationException) {
                    Error(e)
                }
            }
            is Error -> futureWeatherResponse
        }
    }

    private fun WeatherEntity.toWeather() = Weather(
            locationName = this.locationName,
            tempCelsius = this.tempCelsius,
            tempFahrenheit = celsiusToFahrenheit(this.tempCelsius),
            isCloudy = this.cloudiness > 50,
            windSpeed = this.windSpeed
    )

    private fun celsiusToFahrenheit(temperatureInCelsius: Float) = temperatureInCelsius * 1.8f + 32

    private fun List<Float>.calculateStandardDeviation(): Double {
        if (this.size <= 1) {
            //Unable to calculate standard deviation, unable to divide by 0 or less
            throw InvalidCalculationException("Unable to calculate standard deviation")
        }
        val n = this.size
        val mean = this.average()
        val pow = this.map { temperature ->
            (temperature - mean).pow(2)
        }.sum()
        return sqrt(pow / (n - 1))
    }
}
