package com.jnicomedes.myapplication.data.remote.repository

import com.jnicomedes.myapplication.data.domain.core.Either
import com.jnicomedes.myapplication.data.domain.repository.WelcomeRepository
import com.jnicomedes.myapplication.data.remote.core.requestWrapper
import com.jnicomedes.myapplication.data.remote.model.WelcomeRemote
import com.jnicomedes.myapplication.data.remote.service.WelcomeService

class WelcomeRepositoryImpl(private val service: WelcomeService) : WelcomeRepository {
    override suspend fun getWelcome(): Either<WelcomeRemote, Exception> = requestWrapper {
        service.getData()
    }
}

