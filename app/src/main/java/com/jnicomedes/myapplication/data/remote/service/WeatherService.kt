package com.jnicomedes.myapplication.data.remote.service

import com.jnicomedes.myapplication.data.remote.model.WeatherContentRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("mobile-take-home/{id}.json")
    suspend fun getConsolidateWeather(
        @Path("id") id: Long = 4418,
    ): WeatherContentRemote
}