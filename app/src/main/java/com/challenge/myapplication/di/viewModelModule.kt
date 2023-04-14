package com.challenge.myapplication.di

import com.challenge.myapplication.ui.url_shortener.UrlShortenerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UrlShortenerViewModel(getShortenedUrlUseCase = get()) }
}