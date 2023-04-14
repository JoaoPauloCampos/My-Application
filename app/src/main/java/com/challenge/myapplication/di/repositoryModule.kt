package com.challenge.myapplication.di

import com.challenge.myapplication.data.domain.core.CoroutineContextProvider
import com.challenge.myapplication.data.domain.repository.UrlShortenerRepository
import com.challenge.myapplication.data.remote.core.RequestWrapper
import com.challenge.myapplication.data.remote.core.RequestWrapperImpl
import com.challenge.myapplication.data.remote.repository.UrlShortenerRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<RequestWrapper> { RequestWrapperImpl() }
    single { CoroutineContextProvider() }
    factory<UrlShortenerRepository> { UrlShortenerRepositoryImpl(service = get(), requestWrapper = get()) }
}