package com.jnicomedes.myapplication.ui.weather

import com.jnicomedes.myapplication.data.domain.interactor.GetWeatherUseCase
import com.jnicomedes.myapplication.data.domain.model.Weather
import io.kotlintest.shouldBe
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.invoke
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class WeatherViewModelTest {
    private val getWeatherUseCase = mockk<GetWeatherUseCase>()
    private lateinit var viewModel: WeatherViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN WeatherViewModel it's created WHEN getWeatherUseCase returns Success THEN weatherState should be WeatherState Success`() =
        runTest(testDispatcher) {
            // GIVEN
            val expectedResult = mockk<Weather>()
            coEvery {
                getWeatherUseCase(
                    scope = any(),
                    onSuccess = captureLambda(),
                    onError = any()
                )
            } coAnswers {
                lambda<(Weather) -> Unit>().invoke(expectedResult)
            }

            // WHEN
            viewModel = WeatherViewModel(getWeatherUseCase)

            // THEN
            testDispatcher.scheduler.advanceUntilIdle()
            viewModel.weatherState.value shouldBe WeatherState.Success(expectedResult)
        }

    @Test
    fun `GIVEN WeatherViewModel it's created WHEN getWeatherUseCase returns Failure THEN weatherState should be WeatherState Error`() =
        runTest(testDispatcher) {
            // GIVEN
            val expectedResult = Exception("Any Exception")
            coEvery {
                getWeatherUseCase(
                    scope = any(),
                    onSuccess = any(),
                    onError = captureLambda()
                )
            } coAnswers {
                lambda<((Exception) -> Unit)>().invoke(expectedResult)
            }

            // WHEN
            viewModel = WeatherViewModel(getWeatherUseCase)

            // THEN
            testDispatcher.scheduler.advanceUntilIdle()
            viewModel.weatherState.value shouldBe WeatherState.Error("java.lang.Exception: Any Exception")
        }

    @Test
    fun `GIVEN WeatherViewModel it's created THEN weatherState should be initial state WeatherState Loading`() {
        // GIVEN
        coEvery {
            getWeatherUseCase(
                scope = any(),
                onSuccess = any(),
                onError = any()
            )
        } just Runs

        // WHEN
        viewModel = WeatherViewModel(getWeatherUseCase)

        // THEN
        viewModel.weatherState.value shouldBe WeatherState.Loading
    }
}