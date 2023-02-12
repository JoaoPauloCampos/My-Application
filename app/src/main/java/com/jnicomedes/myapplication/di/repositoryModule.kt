package com.jnicomedes.myapplication.di

import com.jnicomedes.myapplication.data.domain.core.CoroutineContextProvider
import com.jnicomedes.myapplication.data.domain.repository.WeatherRepository
import com.jnicomedes.myapplication.data.remote.core.RequestWrapper
import com.jnicomedes.myapplication.data.remote.core.RequestWrapperImpl
import com.jnicomedes.myapplication.data.remote.repository.WeatherRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<RequestWrapper> { RequestWrapperImpl() }
    single { CoroutineContextProvider() }
    factory<WeatherRepository> { WeatherRepositoryImpl(service = get(), requestWrapper = get()) }
}