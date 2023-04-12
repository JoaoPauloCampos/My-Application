package com.jnicomedes.myapplication.di

import com.jnicomedes.myapplication.data.domain.interactor.GetShortenedUrlUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetShortenedUrlUseCase(repository = get(), coroutineContextProvider = get())
    }
}