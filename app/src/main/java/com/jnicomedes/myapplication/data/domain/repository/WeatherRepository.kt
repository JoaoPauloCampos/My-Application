package com.jnicomedes.myapplication.data.domain.repository

import com.jnicomedes.myapplication.data.domain.core.Either
import com.jnicomedes.myapplication.data.domain.model.Weather

interface WeatherRepository {
    suspend fun getConsolidatedWeather(): Either<Weather, Exception>
}