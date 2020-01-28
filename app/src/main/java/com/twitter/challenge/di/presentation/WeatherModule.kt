package com.twitter.challenge.di.presentation

import com.twitter.challenge.ui.weather.WeatherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): WeatherActivity

}
