package com.twitter.challenge.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twitter.challenge.R
import com.twitter.challenge.common.Event
import com.twitter.challenge.common.Result
import com.twitter.challenge.domain.ViewWeatherUseCase
import com.twitter.challenge.model.TempStandDeviation
import com.twitter.challenge.model.Weather
import kotlinx.coroutines.launch

class WeatherViewModel(
        private val weatherUseCase: ViewWeatherUseCase
) : ViewModel(), ViewWeatherViewModel {

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
                is Result.Success -> currentWeather.value = result.data
                is Error -> setErrorMessage(R.string.current_weather_error)
            }
        }
    }

    override fun getStandDeviationForFutureDays(days: Int) {
        viewModelScope.launch {
            loading.value = true
            val result = weatherUseCase.getFutureWeatherStandardDeviation(days)
            when (result) {
                is Result.Success -> temperatureStandDeviation.value = result.data
                is Error -> setErrorMessage(R.string.standard_deviation_error)
            }
            loading.value = false
        }
    }


    private fun setErrorMessage(message: Int) {
        errorMessage.value = Event(message)
    }
}
