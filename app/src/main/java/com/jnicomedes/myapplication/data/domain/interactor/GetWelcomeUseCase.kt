package com.jnicomedes.myapplication.data.domain.interactor

import com.jnicomedes.myapplication.data.domain.core.Either
import com.jnicomedes.myapplication.data.domain.core.ThreadContextProvider
import com.jnicomedes.myapplication.data.domain.core.UseCase
import com.jnicomedes.myapplication.data.domain.repository.WelcomeRepository
import com.jnicomedes.myapplication.data.remote.model.WelcomeRemote
import kotlinx.coroutines.CoroutineScope

class GetWelcomeUseCase(
    private val repository: WelcomeRepository,
    threadContextProvider: ThreadContextProvider
) : UseCase<WelcomeRemote, Unit>(threadContextProvider) {
    override suspend fun run(params: Unit?): Either<WelcomeRemote, Exception> {
        return repository.getWelcome()
    }
}