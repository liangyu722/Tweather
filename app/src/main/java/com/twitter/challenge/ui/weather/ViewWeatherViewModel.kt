package com.twitter.challenge.ui.weather

import androidx.lifecycle.LiveData
import com.twitter.challenge.common.Event
import com.twitter.challenge.model.TempStandDeviation
import com.twitter.challenge.model.Weather

interface ViewWeatherViewModel {

    val loading: LiveData<Boolean>
    val errorMessage: LiveData<Event<Int>>
    val currentWeather: LiveData<Weather>
    val temperatureStandDeviation: LiveData<TempStandDeviation>

    fun getStandDeviationForFutureDays(days: Int)
}
