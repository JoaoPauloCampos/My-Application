package com.jnicomedes.myapplication.di

import com.jnicomedes.myapplication.data.domain.core.ThreadContextProvider
import com.jnicomedes.myapplication.data.domain.repository.WelcomeRepository
import com.jnicomedes.myapplication.data.remote.repository.WelcomeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { ThreadContextProvider() }
    factory<WelcomeRepository> { WelcomeRepositoryImpl(service = get()) }
}