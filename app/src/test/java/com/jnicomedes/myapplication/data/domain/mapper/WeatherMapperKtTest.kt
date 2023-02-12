package com.jnicomedes.myapplication.data.domain.mapper

import com.jnicomedes.myapplication.data.domain.model.Weather
import com.jnicomedes.myapplication.data.remote.model.WeatherContentRemote
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

internal class WeatherMapperKtTest {

    @Test
    fun `GIVEN a valid WeatherContentRemote WHEN toDomain() is called THEN result MUST be a valid object`() {
        // GIVEN
        val weatherContentRemote = mockk<WeatherContentRemote>(relaxed = true) {
            every { title } returns "Toronto"
            every { woeId } returns 1234
            every { consolidatedWeather.first() } returns mockk {
                every { minTemp } returns 1.0
                every { maxTemp } returns 10.0
                every { theTemp } returns 5.0
                every { weatherStateAbbr } returns "lr"
                every { weatherStateName } returns "Light Rain"
            }
        }

        // WHEN
        val result: Weather = weatherContentRemote.toDomain()

        // THEN
        result shouldNotBe null
        result.title shouldBe "Toronto"
        result.id shouldNotBe 4805883302248448
        result.minTemp shouldBe "1°"
        result.maxTemp shouldBe "10°"
        result.currentTemp shouldBe "5°"
        result.condition shouldBe "Light Rain"
        result.image shouldBe "https://cdn.faire.com/static/mobile-take-home/icons/lr.png"
    }

    @Test(expected = NoSuchElementException::class)
    fun `GIVEN a invalid WeatherContentRemote json WHEN toDomain() is called THEN throws Exception`() {
        // GIVEN
        val weatherContentRemote = mockk<WeatherContentRemote>(relaxed = true)

        // WHEN
        val result: Weather = weatherContentRemote.toDomain()

        // THEN
        // Method threw 'java.util.NoSuchElementException' exception.
    }
}