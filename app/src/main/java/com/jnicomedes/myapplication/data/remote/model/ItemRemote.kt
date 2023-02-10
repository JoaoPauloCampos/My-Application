package com.jnicomedes.myapplication.data.remote.model

import com.squareup.moshi.Json

data class ItemRemoteItem(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "link") val link: String,
    @field:Json(name = "media") val media: MediaRemote,
    @field:Json(name = "date_taken") val dateTaken: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "published") val published: String,
    @field:Json(name = "author") val author: String,
    @field:Json(name = "author_id") val authorID: String,
    @field:Json(name = "tags") val tags: String
)