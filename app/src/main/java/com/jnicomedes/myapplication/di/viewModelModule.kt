package com.jnicomedes.myapplication.di

import com.jnicomedes.myapplication.ui.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { WeatherViewModel(getWeatherUseCase = get()) }
}