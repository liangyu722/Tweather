package com.twitter.challenge.ui.weather

import android.os.Bundle
import com.twitter.challenge.R
import com.twitter.challenge.data.remote.RemoteWeatherDataSource
import com.twitter.challenge.data.remote.network.WeatherService
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherActivity : DaggerAppCompatActivity() {

    @Inject lateinit var weatherService: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val remoteDataSource = RemoteWeatherDataSource(weatherService)
        GlobalScope.launch {
            remoteDataSource.getFutureWeatherEntry(150).toString()
        }

    }
}

