package com.challenge.myapplication.data.remote.repository

import com.challenge.myapplication.data.domain.core.Either
import com.challenge.myapplication.data.domain.model.User
import com.challenge.myapplication.data.domain.repository.UserRepository
import com.challenge.myapplication.data.remote.core.RequestWrapper
import com.challenge.myapplication.data.remote.service.UserService

class UserRepositoryImpl(private val service: UserService, private val requestWrapper: RequestWrapper) :
    UserRepository {
    override suspend fun getUsers(): Either<List<User>, Exception> = requestWrapper.wrapper {
        service.getUsers().toMap().toDomainList()
    }
}

fun Any.toMap() = this as Map<*, *>

fun Map<*, *>.toDomainList() = this.map { User(id = this["id"].toString(), name = this["name"].toString()) }