package com.jnicomedes.myapplication.data.remote.repository

import com.jnicomedes.myapplication.data.domain.core.Either
import com.jnicomedes.myapplication.data.domain.mapper.toDomain
import com.jnicomedes.myapplication.data.domain.model.UrlData
import com.jnicomedes.myapplication.data.domain.repository.UrlShortenerRepository
import com.jnicomedes.myapplication.data.remote.core.RequestWrapper
import com.jnicomedes.myapplication.data.remote.model.request.ShortenUrlRequest
import com.jnicomedes.myapplication.data.remote.service.UrlShortenerService

class UrlShortenerRepositoryImpl(
    private val service: UrlShortenerService,
    private val requestWrapper: RequestWrapper
) : UrlShortenerRepository {
    override suspend fun shortenUrl(url: String): Either<UrlData, Exception> = requestWrapper.wrapper {
        service.shortenUrl(
            ShortenUrlRequest(url = url)
        ).toDomain()
    }
}

