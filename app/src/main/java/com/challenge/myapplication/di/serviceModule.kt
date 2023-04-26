package com.challenge.myapplication.di

import com.challenge.myapplication.data.remote.core.BASE_URL
import com.challenge.myapplication.data.remote.core.BASE_URL_2
import com.challenge.myapplication.data.remote.core.ServiceFactory
import com.challenge.myapplication.data.remote.core.ServiceFactory.createWebService
import com.challenge.myapplication.data.remote.service.UrlShortenerService
import com.challenge.myapplication.data.remote.service.UserService
import org.koin.dsl.bind
import org.koin.dsl.module

val serviceModule = module {

    single {
        ServiceFactory.provideOkHttpClient()
    }

    factory {
        BASE_URL.createWebService(okHttpClient = get()) as UrlShortenerService
    } bind UrlShortenerService::class

    factory {
        BASE_URL_2.createWebService(okHttpClient = get()) as UserService
    } bind UserService::class
}
