package com.challenge.myapplication.data.domain.repository

import com.challenge.myapplication.data.domain.core.Either
import com.challenge.myapplication.data.domain.model.User

interface UserRepository {
    suspend fun getUsers(): Either<List<User>, Exception>
}