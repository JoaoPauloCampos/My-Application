package com.challenge.myapplication.data.remote.model.response

import com.squareup.moshi.Json

data class ShortenUrlRemote(
    @field:Json(name = "_links")
    val linkData: LinkDataRemote,
)
