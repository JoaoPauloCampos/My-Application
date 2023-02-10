package com.jnicomedes.myapplication.app

import android.app.Application
import com.jnicomedes.myapplication.di.repositoryModule
import com.jnicomedes.myapplication.di.serviceModule
import com.jnicomedes.myapplication.di.useCaseModule
import com.jnicomedes.myapplication.di.viewModelModule
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
