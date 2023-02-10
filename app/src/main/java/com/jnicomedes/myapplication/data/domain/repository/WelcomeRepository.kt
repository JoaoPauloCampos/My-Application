package com.jnicomedes.myapplication.data.domain.repository

import com.jnicomedes.myapplication.data.domain.core.Either
import com.jnicomedes.myapplication.data.remote.model.WelcomeRemote

interface WelcomeRepository {
    suspend fun getWelcome(): Either<WelcomeRemote, Exception>
}