package com.jnicomedes.myapplication.data.domain.interactor

import com.jnicomedes.myapplication.TestCoroutineContextProvider
import com.jnicomedes.myapplication.data.domain.core.Either
import com.jnicomedes.myapplication.data.domain.repository.UrlShortenerRepository
import io.kotlintest.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class GetUrlDataUseCaseTest {

    private val urlShortenerRepository = mockk<UrlShortenerRepository>()
    private val contextProvider = TestCoroutineContextProvider()

    @Test
    fun `WHEN getShortenedUrlUseCase is called and urlShortenerRepository return Either Success THEN result MUST BE Success`() =
        runBlocking {
            // GIVEN
            coEvery { urlShortenerRepository.shortenUrl(any()) } returns Either.Success(mockk(relaxed = true))
            val getShortenedUrlUseCase = GetShortenedUrlUseCase(urlShortenerRepository, contextProvider)

            // WHEN
            val result = getShortenedUrlUseCase.run(params = "www.google.com")

            // THEN
            result.isSuccess shouldBe true
            result.isFailure shouldBe false
        }

    @Test
    fun `WHEN getShortenedUrlUseCase is called and urlShortenerRepository return Either Failure THEN result MUST BE Failure`() =
        runBlocking {
            // GIVEN
            coEvery { urlShortenerRepository.shortenUrl(any()) } returns Either.Failure(mockk(relaxed = true))
            val getShortenedUrlUseCase = GetShortenedUrlUseCase(urlShortenerRepository, contextProvider)

            // WHEN
            val result = getShortenedUrlUseCase.run(params = "www.google.com")

            // THEN
            result.isSuccess shouldBe false
            result.isFailure shouldBe true
        }

    @Test
    fun `WHEN getShortenedUrlUseCase is called with empty param THEN result MUST BE Failure`() =
        runBlocking {
            // GIVEN
            val getShortenedUrlUseCase = GetShortenedUrlUseCase(urlShortenerRepository, contextProvider)

            // WHEN
            val result = getShortenedUrlUseCase.run("")

            // THEN
            result.isSuccess shouldBe false
            result.isFailure shouldBe true
        }

    @Test
    fun `WHEN getShortenedUrlUseCase is called without param THEN result MUST BE Failure`() =
        runBlocking {
            // GIVEN
            val getShortenedUrlUseCase = GetShortenedUrlUseCase(urlShortenerRepository, contextProvider)

            // WHEN
            val result = getShortenedUrlUseCase.run(params = null)

            // THEN
            result.isSuccess shouldBe false
            result.isFailure shouldBe true
        }
}