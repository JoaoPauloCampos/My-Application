package com.challenge.myapplication.di

import com.challenge.myapplication.data.domain.interactor.GetShortenedUrlUseCase
import com.challenge.myapplication.data.domain.interactor.GetUser
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetShortenedUrlUseCase(repository = get(), coroutineContextProvider = get())
    }
    factory {
        GetUser(repository = get(), coroutineContextProvider = get())
    }
}