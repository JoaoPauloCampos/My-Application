package com.jnicomedes.myapplication.data.domain.mapper

import com.jnicomedes.myapplication.data.domain.model.UrlData
import com.jnicomedes.myapplication.data.remote.model.response.ShortenUrlRemote
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import org.junit.Test


internal class ShortenUrlMapperTest {

    @Test
    fun `GIVEN a valid ShortenUrlRemote WHEN toDomain() is called THEN result MUST be a valid object`() {
        // GIVEN
        val shortenUrlRemote = mockk<ShortenUrlRemote>(relaxed = true) {
            every { linkData } returns mockk {
                every { self } returns "www.google.com"
                every { short } returns "www.gl.gz"
            }
        }

        // WHEN
        val result: UrlData = shortenUrlRemote.toDomain()

        // THEN
        result shouldNotBe null
        result.originalUrl shouldBe "www.google.com"
        result.shortenedUrl shouldBe "www.gl.gz"
    }

    @Test(expected = NullPointerException::class)
    fun `GIVEN a invalid ShortenUrlRemote json WHEN toDomain() is called THEN throws Exception`() {
        // GIVEN
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<ShortenUrlRemote> = moshi.adapter(ShortenUrlRemote::class.java)
        val jsonString = "{\"alias\":\"crOVW\",\"_links\":{\"self\":null,\"short\":\"null\"}}"
        val shortenUrlRemote = jsonAdapter.fromJson(jsonString)

        // WHEN
        shortenUrlRemote!!.toDomain()

        // THEN
        // Method threw 'java.lang.NullPointerException' exception.
    }
}