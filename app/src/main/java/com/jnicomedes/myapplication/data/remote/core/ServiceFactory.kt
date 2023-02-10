package com.jnicomedes.myapplication.data.remote.core

import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.flickr.com/services/"

object ServiceFactory {

    inline fun <reified T> createWebService(
        okHttpClient: OkHttpClient,
        url: String = BASE_URL
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(UnitConverterFactory)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create()
    }

    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .dispatcher(dispatcher())
            .addInterceptor(httpLoggingInterceptor())
            .connectTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .build()

    private fun httpLoggingInterceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private fun dispatcher() =
        Dispatcher().run {
            maxRequests = 1
            maxRequestsPerHost = 1
            this
        }

    object UnitConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type, annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *>? {
            return if (type == Unit::class.java) UnitConverter else null
        }

        private object UnitConverter : Converter<ResponseBody, Unit> {
            override fun convert(value: ResponseBody) {
                value.close()
            }
        }
    }
}