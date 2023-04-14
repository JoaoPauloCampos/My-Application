package com.challenge.myapplication.data.domain.repository

import com.challenge.myapplication.data.domain.core.Either
import com.challenge.myapplication.data.domain.model.UrlData

interface UrlShortenerRepository {
    suspend fun shortenUrl(url: String): Either<UrlData, Exception>
}