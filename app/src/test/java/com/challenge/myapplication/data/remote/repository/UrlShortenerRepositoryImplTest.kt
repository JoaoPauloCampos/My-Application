package com.challenge.myapplication.data.remote.repository

import com.challenge.myapplication.data.domain.repository.UrlShortenerRepository
import com.challenge.myapplication.data.remote.core.RequestWrapperImpl
import com.challenge.myapplication.data.remote.model.response.ShortenUrlRemote
import com.challenge.myapplication.data.remote.service.UrlShortenerService
import io.kotlintest.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class UrlShortenerRepositoryImplTest {

    private val urlShortenerService = mockk<UrlShortenerService>(relaxed = true)
    private val urlShortenerRepository: UrlShortenerRepository =
        UrlShortenerRepositoryImpl(urlShortenerService, RequestWrapperImpl())
    private val shortenUrlRemote = mockk<ShortenUrlRemote>(relaxed = true) {
        every { linkData } returns mockk {
            every { short } returns "www.google.com"
            every { self } returns "www.gl.gz"
        }
    }

    @Test
    fun `GIVEN UrlShortenerRepository WHEN shortenUrl() is called THEN result MUST be Success`() = runBlocking {
        // GIVE
        coEvery { urlShortenerService.shortenUrl(any()) } returns shortenUrlRemote

        // WHEN
        val result = urlShortenerRepository.shortenUrl("www.google.com")

        // THEN
        coVerify(exactly = 1) { urlShortenerService.shortenUrl(any()) }
        result.isSuccess shouldBe true
        result.isFailure shouldBe false
    }

    @Test
    fun `GIVEN UrlShortenerRepository WHEN service returns any Exception THEN result MUST be Failure`() =
        runBlocking {
            // GIVEN
            coEvery { urlShortenerService.shortenUrl(any()) } throws IllegalArgumentException("Any Exception")

            // WHEN
            val result = urlShortenerRepository.shortenUrl("")

            // THEN
            coVerify(exactly = 1) { urlShortenerService.shortenUrl(any()) }
            result.isSuccess shouldBe false
            result.isFailure shouldBe true
        }
}