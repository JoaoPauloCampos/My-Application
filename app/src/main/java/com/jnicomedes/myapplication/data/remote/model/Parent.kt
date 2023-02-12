package com.jnicomedes.myapplication.data.remote.model

import com.squareup.moshi.Json

data class Parent(
    val title: String,

    @field:Json(name = "location_type")
    val locationType: String,

    @field:Json(name = "woeid")
    val woeId: Long,

    @field:Json(name = "latt_long")
    val latLong: String
)
