package com.twitter.challenge.di

import android.content.Context
import com.twitter.challenge.WeatherApplication
import com.twitter.challenge.di.domain.DomainModule
import com.twitter.challenge.di.network.ServiceModule
import com.twitter.challenge.di.presentation.WeatherModule
import com.twitter.challenge.di.repository.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ServiceModule::class,
            DataModule::class,
            DomainModule::class,
            WeatherModule::class
        ]
)
interface ApplicationComponent : AndroidInjector<WeatherApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}
