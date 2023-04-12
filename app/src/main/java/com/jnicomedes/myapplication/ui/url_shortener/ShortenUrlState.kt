package com.jnicomedes.myapplication.ui.url_shortener

import androidx.annotation.StringRes

sealed class ShortenUrlState {
    data class Error(@StringRes val errorMessage: Int) : ShortenUrlState()
    object Success : ShortenUrlState()
    object Loading : ShortenUrlState()
    object Neutral : ShortenUrlState()
}

@StringRes
fun ShortenUrlState.errorMessage(): Int? = when (this) {
    is ShortenUrlState.Error -> {
        errorMessage
    }
    else -> null
}