package com.twitter.challenge.ui.weather

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.twitter.challenge.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class WeatherActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ViewWeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory).get(WeatherViewModel::class.java)
        viewModel.currentWeather

    }
}

