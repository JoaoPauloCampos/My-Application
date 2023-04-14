package com.challenge.myapplication.di

import com.challenge.myapplication.data.remote.core.BASE_URL
import com.challenge.myapplication.data.remote.core.ServiceFactory
import com.challenge.myapplication.data.remote.core.ServiceFactory.createWebService
import com.challenge.myapplication.data.remote.service.UrlShortenerService
import org.koin.dsl.bind
import org.koin.dsl.module

val serviceModule = module {

    single {
        ServiceFactory.provideOkHttpClient()
    }

    factory {
        BASE_URL.createWebService(okHttpClient = get()) as UrlShortenerService
    } bind UrlShortenerService::class
}
