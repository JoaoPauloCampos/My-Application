package com.jnicomedes.myapplication.di

import com.jnicomedes.myapplication.data.domain.interactor.GetWelcomeUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetWelcomeUseCase(repository = get(), threadContextProvider = get())
    }
}