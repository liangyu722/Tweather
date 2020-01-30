package com.twitter.challenge.ui.weather

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.twitter.challenge.R
import com.twitter.challenge.model.TempStandDeviation
import com.twitter.challenge.model.Weather

@BindingAdapter("app:temperature")
fun setTemperature(textView: TextView, currentWeather: Weather?) {
    if (currentWeather == null) {
        return
    }
    textView.text = textView.resources.getString(
            R.string.temperature,
            currentWeather.tempCelsius,
            currentWeather.tempFahrenheit
    )
}

@BindingAdapter("app:temperatureStandardDeviation")
fun setTemperatureStandardDeviation(textView: TextView, tempStandDeviation: TempStandDeviation?) {
    if (tempStandDeviation == null) {
        return
    }
    textView.text = textView.resources.getString(
            R.string.standard_deviation_temperature,
            tempStandDeviation.standDeviationCelsius,
            tempStandDeviation.standDeviationFahrenheit
    )
}
