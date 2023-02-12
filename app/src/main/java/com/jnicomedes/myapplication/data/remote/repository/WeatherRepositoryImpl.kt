package com.jnicomedes.myapplication.data.remote.repository

import com.jnicomedes.myapplication.data.domain.core.Either
import com.jnicomedes.myapplication.data.domain.mapper.toDomain
import com.jnicomedes.myapplication.data.domain.model.Weather
import com.jnicomedes.myapplication.data.domain.repository.WeatherRepository
import com.jnicomedes.myapplication.data.remote.core.RequestWrapper
import com.jnicomedes.myapplication.data.remote.service.WeatherService

class WeatherRepositoryImpl(private val service: WeatherService, private val requestWrapper: RequestWrapper) :
    WeatherRepository {
    override suspend fun getConsolidatedWeather(): Either<Weather, Exception> = requestWrapper.wrapper {
        service.getConsolidateWeather().toDomain()
    }
}

