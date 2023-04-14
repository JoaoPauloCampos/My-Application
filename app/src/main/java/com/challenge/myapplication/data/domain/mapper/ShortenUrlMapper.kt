package com.challenge.myapplication.data.domain.mapper

import com.challenge.myapplication.data.domain.model.UrlData
import com.challenge.myapplication.data.remote.model.response.ShortenUrlRemote

fun ShortenUrlRemote.toDomain(): UrlData = UrlData(originalUrl = linkData.self, shortenedUrl = linkData.short)