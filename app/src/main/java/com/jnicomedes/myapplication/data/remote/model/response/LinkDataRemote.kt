package com.jnicomedes.myapplication.data.remote.model.response

import com.squareup.moshi.Json

data class LinkDataRemote(
    @field:Json(name = "short")
    val short: String,

    @field:Json(name = "self")
    val self: String
)
