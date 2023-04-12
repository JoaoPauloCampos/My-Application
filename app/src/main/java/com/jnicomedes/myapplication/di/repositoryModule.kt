package com.jnicomedes.myapplication.di

import com.jnicomedes.myapplication.data.domain.core.CoroutineContextProvider
import com.jnicomedes.myapplication.data.domain.repository.UrlShortenerRepository
import com.jnicomedes.myapplication.data.remote.core.RequestWrapper
import com.jnicomedes.myapplication.data.remote.core.RequestWrapperImpl
import com.jnicomedes.myapplication.data.remote.repository.UrlShortenerRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<RequestWrapper> { RequestWrapperImpl() }
    single { CoroutineContextProvider() }
    factory<UrlShortenerRepository> { UrlShortenerRepositoryImpl(service = get(), requestWrapper = get()) }
}