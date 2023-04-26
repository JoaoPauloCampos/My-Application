package com.challenge.myapplication.data.remote.model.response

import com.squareup.moshi.Json

data class UserRemote(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "name")
    val name: String
)