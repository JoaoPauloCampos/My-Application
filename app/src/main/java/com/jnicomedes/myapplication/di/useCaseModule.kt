package com.jnicomedes.myapplication.di

import com.jnicomedes.myapplication.data.domain.interactor.GetWeatherUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetWeatherUseCase(repository = get(), coroutineContextProvider = get())
    }
}