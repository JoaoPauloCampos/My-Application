package com.jnicomedes.myapplication.data.domain.mapper

import com.jnicomedes.myapplication.data.domain.model.UrlData
import com.jnicomedes.myapplication.data.remote.model.response.ShortenUrlRemote

fun ShortenUrlRemote.toDomain(): UrlData = UrlData(originalUrl = linkData.self, shortenedUrl = linkData.short)