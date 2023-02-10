package com.jnicomedes.myapplication.data.remote.model

import com.squareup.moshi.Json

data class AuthorIdRemote(
    @field:Json(name = "value") val value: String
)