package com.jnicomedes.myapplication.data.domain.interactor

import com.jnicomedes.myapplication.data.domain.core.CoroutineContextProvider
import com.jnicomedes.myapplication.data.domain.core.Either
import com.jnicomedes.myapplication.data.domain.core.UseCase
import com.jnicomedes.myapplication.data.domain.model.Weather
import com.jnicomedes.myapplication.data.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val repository: WeatherRepository,
    coroutineContextProvider: CoroutineContextProvider
) : UseCase<Weather, Unit>(coroutineContextProvider) {
    override suspend fun run(params: Unit?): Either<Weather, Exception> {
        return repository.getConsolidatedWeather()
    }
}