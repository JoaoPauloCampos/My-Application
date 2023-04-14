package com.challenge.myapplication.data.remote.repository

import com.challenge.myapplication.data.domain.core.Either
import com.challenge.myapplication.data.domain.mapper.toDomain
import com.challenge.myapplication.data.domain.model.UrlData
import com.challenge.myapplication.data.domain.repository.UrlShortenerRepository
import com.challenge.myapplication.data.remote.core.RequestWrapper
import com.challenge.myapplication.data.remote.model.request.ShortenUrlRequest
import com.challenge.myapplication.data.remote.service.UrlShortenerService

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

