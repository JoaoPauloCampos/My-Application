package com.jnicomedes.myapplication.ui.url_shortener

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jnicomedes.myapplication.core.InstrumentedTestApplication
import com.jnicomedes.myapplication.core.TestCoroutineContextProvider
import com.jnicomedes.myapplication.data.domain.core.Either
import com.jnicomedes.myapplication.data.domain.interactor.GetShortenedUrlUseCase
import com.jnicomedes.myapplication.data.domain.model.UrlData
import com.jnicomedes.myapplication.data.domain.repository.UrlShortenerRepository
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class UrlShortenerScreenTest {
    private val application =
        InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as InstrumentedTestApplication
    private val repository = mockk<UrlShortenerRepository>(relaxed = true, relaxUnitFun = true)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        application.startDependencyInjection(modules = listOf(
            module {
                factory { repository }
                factory { GetShortenedUrlUseCase(get(), TestCoroutineContextProvider()) }
                viewModel { UrlShortenerViewModel(getShortenedUrlUseCase = get()) }
            }
        ))
    }

    @After
    fun tearDown() {
        application.stopDependencyInjection()
    }

    @Test
    fun given_UrlShortenerScreen_When_UserInputText_And_ShortenUrlIsSuccess_Then_UrlCardShouldBeVisible() {
        coEvery { repository.shortenUrl("www.google.com") } coAnswers {
            Either.Success(UrlData(originalUrl = "www.google.com", shortenedUrl = "www.g.com"))
        }
        composeTestRule.setContent { UrlShortenerScreen() }

        composeTestRule.onNodeWithTag("UrlTextField").performTextInput("www.google.com")
        composeTestRule.onNodeWithTag("ShortenUrlButton").performClick()
        composeTestRule.onNodeWithTag("TextError").assertDoesNotExist()
        composeTestRule.onAllNodesWithTag("UrlCardItem")[0].assertExists()
        composeTestRule.onNodeWithTag("OriginalUrl").assertTextEquals("Original URL: www.google.com")
        composeTestRule.onNodeWithTag("ShortUrl").assertTextEquals("Short URL: www.g.com")
    }

    @Test
    fun given_UrlShortenerScreen_When_UserPerformClickWithoutInputText_Then_TextErrorShouldBeVisible() {
        composeTestRule.setContent { UrlShortenerScreen() }
        composeTestRule.onNodeWithTag("ShortenUrlButton").performClick()
        composeTestRule.onNodeWithTag("TextError").assertExists("Oops! Required field!")
    }

    @Test
    fun given_UrlShortenerScreen_When_UserInputText_And_ShortenUrlIsFailure_Then_TextErrorShouldBeVisible() {
        coEvery { repository.shortenUrl("www.google.com") } coAnswers {
            Either.Failure(Exception(""))
        }
        composeTestRule.setContent { UrlShortenerScreen() }

        composeTestRule.onNodeWithTag("UrlTextField").performTextInput("www.google.com")
        composeTestRule.onNodeWithTag("ShortenUrlButton").performClick()
        composeTestRule.onNodeWithTag("TextError").assertExists()
        composeTestRule.onNodeWithTag("TextError").assertExists("Oops! Something went wrong!")
    }
}