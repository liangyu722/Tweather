package com.twitter.challenge.di.presentation

import androidx.lifecycle.ViewModel
import com.twitter.challenge.di.ViewModelBuilder
import com.twitter.challenge.di.ViewModelKey
import com.twitter.challenge.ui.weather.WeatherActivity
import com.twitter.challenge.ui.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class WeatherModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    abstract fun mainActivity(): WeatherActivity

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun bindViewModel(viewmodel: WeatherViewModel): ViewModel

}
