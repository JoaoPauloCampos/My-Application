package com.challenge.myapplication.di

import com.challenge.myapplication.data.domain.interactor.GetShortenedUrlUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetShortenedUrlUseCase(repository = get(), coroutineContextProvider = get())
    }
}