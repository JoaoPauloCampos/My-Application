package com.jnicomedes.myapplication.data.domain.model

data class Welcome(
    val title: String? = null,
    val link: String? = null,
    val description: String? = null,
    val modified: String? = null,
    val generator: String? = null,
    val items: List<ItemRemoteItem>? = null
)