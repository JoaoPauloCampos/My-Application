package com.challenge.myapplication.data.domain.interactor

import com.challenge.myapplication.data.domain.core.CoroutineContextProvider
import com.challenge.myapplication.data.domain.core.Either
import com.challenge.myapplication.data.domain.core.UseCase
import com.challenge.myapplication.data.domain.model.UrlData
import com.challenge.myapplication.data.domain.repository.UrlShortenerRepository

class GetShortenedUrlUseCase(
    private val repository: UrlShortenerRepository,
    coroutineContextProvider: CoroutineContextProvider
) : UseCase<UrlData, String>(coroutineContextProvider) {
    override suspend fun run(params: String?): Either<UrlData, Exception> {
        return if (params.isNullOrBlank()) {
            Either.Failure(IllegalArgumentException("Invalid Input. Please enter a valid url!"))
        } else {
            repository.shortenUrl(params)
        }
    }
}