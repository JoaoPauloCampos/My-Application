package com.jnicomedes.myapplication.data.remote.service

import com.jnicomedes.myapplication.data.remote.model.WelcomeRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WelcomeService {
    @GET("feeds/photos_public.gne")
    suspend fun getData(
        @Query("tags") tags: String = "priime",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Number = 1,
    ): WelcomeRemote
}