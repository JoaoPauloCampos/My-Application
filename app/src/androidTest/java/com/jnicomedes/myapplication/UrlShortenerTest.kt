package com.jnicomedes.myapplication

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jnicomedes.myapplication.data.domain.interactor.GetShortenedUrlUseCase
import com.jnicomedes.myapplication.data.domain.repository.UrlShortenerRepository
import com.jnicomedes.myapplication.ui.url_shortener.UrlShortenerScreen
import com.jnicomedes.myapplication.ui.url_shortener.UrlShortenerViewModel
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class UrlShortenerTest {
    private val application =
        InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as InstrumentedTestApplication
    private val repository = mockk<UrlShortenerRepository>(relaxed = true)

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        application.startDependencyInjection(modules = listOf(
            module {
                repository
                factory { GetShortenedUrlUseCase(get(), TestCoroutineContextProvider()) }
                viewModel { UrlShortenerViewModel(getShortenedUrlUseCase = get()) }
            }
        ))
    }

    @After
    fun baseAfter() {
        application.stopDependencyInjection()
    }

    @Test
    fun openScreen() {
        rule.setContent { UrlShortenerScreen() }
    }
}