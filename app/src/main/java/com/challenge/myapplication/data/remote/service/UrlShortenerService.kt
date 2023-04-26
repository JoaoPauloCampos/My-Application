package com.challenge.myapplication.data.remote.service

import com.challenge.myapplication.data.remote.model.request.ShortenUrlRequest
import com.challenge.myapplication.data.remote.model.response.ShortenUrlRemote
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UrlShortenerService {

    @POST("api/alias")
    suspend fun shortenUrl(
        @Body request: ShortenUrlRequest,
    ): ShortenUrlRemote
}

interface UserService {
    @GET("clients.json")
    suspend fun getUsers(): Any
}