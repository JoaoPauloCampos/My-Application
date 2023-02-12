package com.jnicomedes.myapplication.data.remote.repository

import com.jnicomedes.myapplication.data.domain.repository.WeatherRepository
import com.jnicomedes.myapplication.data.remote.core.RequestWrapperImpl
import com.jnicomedes.myapplication.data.remote.model.WeatherContentRemote
import com.jnicomedes.myapplication.data.remote.service.WeatherService
import io.kotlintest.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class WeatherRepositoryImplTest {

    private val weatherService = mockk<WeatherService>(relaxed = true)
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl(weatherService, RequestWrapperImpl())
    private val weatherContentRemote = mockk<WeatherContentRemote>(relaxed = true) {
        every { title } returns "Toronto"
        every { woeId } returns 1234
    }

    @Test
    fun `GIVEN WeatherRepository WHEN getConsolidateWeather() is called THEN result MUST be Success`() =
        runBlocking {
            // GIVEN
            every { weatherContentRemote.consolidatedWeather.first() } returns mockk {
                every { minTemp } returns 1.0
                every { maxTemp } returns 10.0
                every { theTemp } returns 5.0
                every { weatherStateAbbr } returns "123"
                every { weatherStateName } returns "Light Rain"
            }
            coEvery { weatherService.getConsolidateWeather(any()) } returns weatherContentRemote


            // WHEN
            val result = weatherRepository.getConsolidatedWeather()

            // THEN
            coVerify(exactly = 1) { weatherService.getConsolidateWeather(any()) }
            result.isSuccess shouldBe true
            result.isFailure shouldBe false
        }

    @Test
    fun `GIVEN WeatherRepository WHEN service returns any Exception THEN result MUST be Failure`() =
        runBlocking {
            // GIVEN
            coEvery { weatherService.getConsolidateWeather(any()) } throws IllegalArgumentException("Any Exception")

            // WHEN
            val result = weatherRepository.getConsolidatedWeather()

            // THEN
            coVerify(exactly = 1) { weatherService.getConsolidateWeather(any()) }
            result.isSuccess shouldBe false
            result.isFailure shouldBe true
        }
}