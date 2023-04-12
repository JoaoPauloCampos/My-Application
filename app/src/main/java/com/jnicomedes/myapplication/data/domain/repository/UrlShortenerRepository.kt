package com.jnicomedes.myapplication.data.domain.repository

import com.jnicomedes.myapplication.data.domain.core.Either
import com.jnicomedes.myapplication.data.domain.model.UrlData

interface UrlShortenerRepository {
    suspend fun shortenUrl(url: String): Either<UrlData, Exception>
}