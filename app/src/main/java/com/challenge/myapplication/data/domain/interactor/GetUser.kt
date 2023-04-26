package com.challenge.myapplication.data.domain.interactor

import com.challenge.myapplication.data.domain.core.CoroutineContextProvider
import com.challenge.myapplication.data.domain.core.Either
import com.challenge.myapplication.data.domain.core.UseCase
import com.challenge.myapplication.data.domain.model.User
import com.challenge.myapplication.data.domain.repository.UserRepository

class GetUser(
    private val repository: UserRepository,
    coroutineContextProvider: CoroutineContextProvider
) : UseCase<List<User>, String>(coroutineContextProvider) {
    override suspend fun run(params: String?): Either<List<User>, Exception> {
        return repository.getUsers()
    }
}