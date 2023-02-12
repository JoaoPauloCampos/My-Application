package com.jnicomedes.myapplication.data.domain.interactor

import com.jnicomedes.myapplication.TestCoroutineContextProvider
import com.jnicomedes.myapplication.data.domain.core.Either
import com.jnicomedes.myapplication.data.domain.repository.WeatherRepository
import io.kotlintest.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class GetWeatherUseCaseTest {

    private val weatherRepository = mockk<WeatherRepository>()
    private val contextProvider = TestCoroutineContextProvider()

    @Test
    fun `WHEN getWeatherUseCase is called and weatherRepository return Either Success THEN result MUST BE Success`() =
        runBlocking {
            // GIVEN
            coEvery { weatherRepository.getConsolidatedWeather() } returns Either.Success(mockk(relaxed = true))
            val getWeatherUseCase = GetWeatherUseCase(weatherRepository, contextProvider)

            // WHEN
            val result = getWeatherUseCase.run()

            // THEN
            result.isSuccess shouldBe true
            result.isFailure shouldBe false
        }

    @Test
    fun `WHEN getWeatherUseCase is called and weatherRepository return Either Failure THEN result MUST BE Success`() =
        runBlocking {
            // GIVEN
            coEvery { weatherRepository.getConsolidatedWeather() } returns Either.Failure(mockk(relaxed = true))
            val getWeatherUseCase = GetWeatherUseCase(weatherRepository, contextProvider)

            // WHEN
            val result = getWeatherUseCase.run()

            // THEN
            result.isSuccess shouldBe false
            result.isFailure shouldBe true
        }
}