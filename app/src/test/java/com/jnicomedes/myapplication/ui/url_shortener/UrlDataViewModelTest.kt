package com.jnicomedes.myapplication.ui.url_shortener

import com.jnicomedes.myapplication.R
import com.jnicomedes.myapplication.data.domain.interactor.GetShortenedUrlUseCase
import com.jnicomedes.myapplication.data.domain.model.UrlData
import io.kotlintest.shouldBe
import io.mockk.coEvery
import io.mockk.invoke
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
internal class UrlDataViewModelTest {
    private val getShortenedUrlUseCase = mockk<GetShortenedUrlUseCase>()
    private lateinit var viewModel: UrlShortenerViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN valid input value WHEN getShortenedUrlUseCase returns Success THEN url list should be the expected value `() =
        runTest(testDispatcher) {
            // GIVEN
            val expectedResult = mockk<UrlData>()
            coEvery {
                getShortenedUrlUseCase(
                    scope = any(),
                    params = "www.google.com",
                    onSuccess = captureLambda(),
                    onError = any()
                )
            } coAnswers {
                lambda<(UrlData) -> Unit>().invoke(expectedResult)
            }
            viewModel = UrlShortenerViewModel(getShortenedUrlUseCase)
            viewModel.setInputText("www.google.com")

            // WHEN
            viewModel.shortenUrl()

            // THEN
            testDispatcher.scheduler.advanceUntilIdle()
            viewModel.urlShortenMutableState.value shouldBe ShortenUrlState.Success
            viewModel.urlList.value.first() shouldBe expectedResult
        }

    @Test
    fun `GIVEN an invalid input WHEN getShortenedUrlUseCase throws IllegalArgumentException THEN error message should be invalid_input_error`() =
        runTest(testDispatcher) {
            // GIVEN
            val expectedResult = IllegalArgumentException("Invalid input!")
            coEvery {
                getShortenedUrlUseCase(
                    scope = any(),
                    params = any(),
                    onSuccess = any(),
                    onError = captureLambda()
                )
            } coAnswers {
                lambda<((Exception) -> Unit)>().invoke(expectedResult)
            }
            viewModel = UrlShortenerViewModel(getShortenedUrlUseCase)

            // WHEN
            viewModel.shortenUrl()

            // THEN
            testDispatcher.scheduler.advanceUntilIdle()
            viewModel.urlShortenMutableState.value shouldBe ShortenUrlState.Error(R.string.invalid_input_error)
        }

    @Test
    fun `GIVEN an valid input WHEN getShortenedUrlUseCase throws Any Exception THEN error message should be generic_error`() =
        runTest(testDispatcher) {
            // GIVEN
            val expectedResult = Exception("Any Exception")
            coEvery {
                getShortenedUrlUseCase(
                    scope = any(),
                    params = any(),
                    onSuccess = any(),
                    onError = captureLambda()
                )
            } coAnswers {
                lambda<((Exception) -> Unit)>().invoke(expectedResult)
            }
            viewModel = UrlShortenerViewModel(getShortenedUrlUseCase)

            // WHEN
            viewModel.shortenUrl()

            // THEN
            testDispatcher.scheduler.advanceUntilIdle()
            viewModel.urlShortenMutableState.value shouldBe ShortenUrlState.Error(R.string.generic_error)
        }

    @Test
    fun `GIVEN UrlShortenerViewModel it's created THEN urlShortenMutableState should be initial state Neutral`() {
        // GIVEN
        viewModel = UrlShortenerViewModel(getShortenedUrlUseCase)

        // THEN
        viewModel.urlShortenMutableState.value shouldBe ShortenUrlState.Neutral
    }

    @Test
    fun `GIVEN UrlShortenerViewModel WHEN setInputText is called THEN inputText should be the correct value`() {
        // GIVEN
        viewModel = UrlShortenerViewModel(getShortenedUrlUseCase)

        // WHEN
        viewModel.setInputText("www.nubank.com")

        //THEN
        viewModel.inputText.value shouldBe "www.nubank.com"
    }
}