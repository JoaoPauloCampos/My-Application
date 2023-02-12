package com.jnicomedes.myapplication.ui.weather

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnicomedes.myapplication.data.domain.interactor.GetWeatherUseCase
import com.jnicomedes.myapplication.data.domain.model.Weather

class WeatherViewModel(private val getWeatherUseCase: GetWeatherUseCase) : ViewModel() {

    private val _weatherMutableState = mutableStateOf<WeatherState>(WeatherState.Loading)
    val weatherState: MutableState<WeatherState>
        get() = _weatherMutableState

    init {
        getWelcome()
    }

    private fun getWelcome() {
        getWeatherUseCase(
            scope = viewModelScope,
            onSuccess = {
                _weatherMutableState.value = WeatherState.Success(it)
            },
            onError = {
                _weatherMutableState.value = WeatherState.Error(it.toString())
            }
        )
    }
}

sealed class WeatherState {
    object Loading : WeatherState()
    data class Error(val errorMessage: String) : WeatherState()
    data class Success(val data: Weather) : WeatherState()
}