package com.jnicomedes.myapplication.di

import com.jnicomedes.myapplication.ui.view_model.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeFragmentViewModel(getWelcomeUseCase = get()) }
}