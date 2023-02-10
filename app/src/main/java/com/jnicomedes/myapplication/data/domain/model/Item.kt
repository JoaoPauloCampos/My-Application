package com.jnicomedes.myapplication.data.domain.model

import com.squareup.moshi.Json

data class ItemRemoteItem(
    val title: String,
    val link: String,
    val media: Media,
    @Json(name = "date_taken")
    val dateTaken: String,
    val description: String,
    val published: String,
    val author: Author,
    @Json(name = "author_id")
    val authorID: AuthorId,
    val tags: String
)