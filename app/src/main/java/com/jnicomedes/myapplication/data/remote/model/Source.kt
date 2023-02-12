package com.jnicomedes.myapplication.data.remote.model

import com.squareup.moshi.Json

data class Source(
    @field:Json(name = "crawl_rate")
    val crawlRate: Long,

    val title: String,
    val slug: String,
    val url: String,
)
