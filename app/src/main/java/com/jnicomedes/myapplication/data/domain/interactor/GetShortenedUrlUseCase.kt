package com.jnicomedes.myapplication.data.domain.interactor

import com.jnicomedes.myapplication.data.domain.core.CoroutineContextProvider
import com.jnicomedes.myapplication.data.domain.core.Either
import com.jnicomedes.myapplication.data.domain.core.UseCase
import com.jnicomedes.myapplication.data.domain.model.UrlData
import com.jnicomedes.myapplication.data.domain.repository.UrlShortenerRepository

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