package com.twitter.challenge.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twitter.challenge.R
import com.twitter.challenge.common.Event
import com.twitter.challenge.common.Result.*
import com.twitter.challenge.domain.ViewWeather
import com.twitter.challenge.model.TempStandDeviation
import com.twitter.challenge.model.Weather
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
        private val weatherUseCase: ViewWeather
) : ViewModel(), ViewWeatherViewModel {
    companion object {
        private const val FUTURE_DAY = 5
    }

    override val loading = MutableLiveData<Boolean>()
    override val errorMessage = MutableLiveData<Event<Int>>()
    override val currentWeather = MutableLiveData<Weather>()
    override val temperatureStandDeviation = MutableLiveData<TempStandDeviation>()

    init {
        loadCurrentWeather()
    }

    private fun loadCurrentWeather() {
        viewModelScope.launch {
            loading.value = true
            val result = weatherUseCase.getCurrentWeather()
            when (result) {
                is Success -> currentWeather.value = result.data
                is Error -> setErrorMessage(R.string.current_weather_error)
            }
            loading.value = false
        }
    }

    override fun getStandDeviationForFutureDays() {
        viewModelScope.launch {
            loading.value = true
            val result = weatherUseCase.getFutureWeatherStandardDeviation(FUTURE_DAY)
            when (result) {
                is Success -> temperatureStandDeviation.value = result.data
                is Error -> setErrorMessage(R.string.standard_deviation_error)
            }
            loading.value = false
        }
    }


    private fun setErrorMessage(message: Int) {
        errorMessage.value = Event(message)
    }
}
