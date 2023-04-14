package com.challenge.myapplication.data.remote.core

import io.kotlintest.shouldBe
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

internal class RequestWrapperImplTest {
    private val requestWrapper: RequestWrapper = RequestWrapperImpl()

    @Test
    fun `GIVEN Request Wrapper WHEN wrapper is called with valid result MUST be return Success`() = runBlocking {
        val result = requestWrapper.wrapper {
            "Success"
        }

        result.isSuccess shouldBe true
        result.isFailure shouldBe false
    }

    @Test
    fun `GIVEN Request Wrapper WHEN wrapper throw HttpException MUST be return Failure`() = runBlocking {
        val result = requestWrapper.wrapper {
            throw HttpException(
                Response.error<Any>(400, mockk())
            )
        }

        result.isFailure shouldBe true
        result.isSuccess shouldBe false
    }

    @Test
    fun `GIVEN Request Wrapper WHEN wrapper throw IOException MUST be return Failure`() = runBlocking {
        val result = requestWrapper.wrapper {
            throw IOException("Error")
        }

        result.isFailure shouldBe true
        result.isSuccess shouldBe false
    }

    @Test
    fun `GIVEN Request Wrapper WHEN wrapper throw IllegalStateException MUST be return Failure`() = runBlocking {
        val result = requestWrapper.wrapper {
            throw IllegalStateException("Error")
        }

        result.isFailure shouldBe true
        result.isSuccess shouldBe false
    }

    @Test
    fun `GIVEN Request Wrapper WHEN wrapper throw Exception MUST be return Failure`() = runBlocking {
        val result = requestWrapper.wrapper {
            throw Exception("Error")
        }

        result.isFailure shouldBe true
        result.isSuccess shouldBe false
    }
}