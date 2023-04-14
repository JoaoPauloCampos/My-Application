package com.challenge.myapplication.app

import android.app.Application
import com.challenge.myapplication.di.repositoryModule
import com.challenge.myapplication.di.serviceModule
import com.challenge.myapplication.di.useCaseModule
import com.challenge.myapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                viewModelModule,
                repositoryModule,
                useCaseModule,
                serviceModule,
            ).androidContext(applicationContext)
        }
    }
}
