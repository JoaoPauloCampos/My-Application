package com.jnicomedes.myapplication.di

import com.jnicomedes.myapplication.data.remote.core.BASE_URL
import com.jnicomedes.myapplication.data.remote.core.ServiceFactory
import com.jnicomedes.myapplication.data.remote.service.WeatherService
import org.koin.dsl.bind
import org.koin.dsl.module

val serviceModule = module {

    single {
        ServiceFactory.provideOkHttpClient()
    }

    factory {
        ServiceFactory.createWebService(
            url = BASE_URL,
            okHttpClient = get()
        ) as WeatherService
    } bind WeatherService::class
}
