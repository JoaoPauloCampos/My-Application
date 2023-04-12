package com.jnicomedes.myapplication.ui.url_shortener

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnicomedes.myapplication.R
import com.jnicomedes.myapplication.data.domain.interactor.GetShortenedUrlUseCase
import com.jnicomedes.myapplication.data.domain.model.UrlData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UrlShortenerViewModel(private val getShortenedUrlUseCase: GetShortenedUrlUseCase) : ViewModel() {

    private val _urlList = MutableStateFlow<MutableList<UrlData>>(mutableStateListOf())
    val urlList get() = _urlList

    private val _urlShortenMutableState = mutableStateOf<ShortenUrlState>(ShortenUrlState.Neutral)
    val urlShortenMutableState: MutableState<ShortenUrlState>
        get() = _urlShortenMutableState

    private val _inputText = MutableStateFlow("")
    val inputText = _inputText.asStateFlow()

    fun setInputText(text: String) {
        _inputText.value = text
    }

    fun shortenUrl() {
        _urlShortenMutableState.value = ShortenUrlState.Loading
        getShortenedUrlUseCase(
            scope = viewModelScope,
            params = _inputText.value,
            onSuccess = {
                handleResultSuccess(it)
            },
            onError = {
                handleResultError(it)
            }
        )
    }

    private fun handleResultError(exception: Exception) {
        val errorMessage = when (exception) {
            is IllegalArgumentException -> R.string.invalid_input_error
            else -> R.string.generic_error
        }
        urlShortenMutableState.value = ShortenUrlState.Error(errorMessage = errorMessage)
    }

    private fun handleResultSuccess(it: UrlData) {
        _urlList.value.add(it)
        _inputText.value = ""
        urlShortenMutableState.value = ShortenUrlState.Success
    }
}