package com.challenge.myapplication.data.domain.model

import com.challenge.myapplication.data.remote.model.response.UserRemote

data class User(
    val id: String,
    val name: String
)

fun UserRemote.toDomain() = User(id, name)