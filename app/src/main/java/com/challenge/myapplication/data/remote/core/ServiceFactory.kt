package com.challenge.myapplication.data.remote.core

import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

//const val BASE_URL = "https://url-shortener-nu.herokuapp.com/"
const val BASE_URL = "https://private-5acb67-nubanktest.apiary-mock.com/"
const val BASE_URL_2 = "https://my-app.firebaseio.com/"
//const val BASE_URL = "http://10.0.2.2:3000/"

object ServiceFactory {

    inline fun <reified T> String.createWebService(okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(this)
            .client(okHttpClient)
            .addConverterFactory(UnitConverterFactory)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create()
    }

    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .dispatcher(dispatcher())
        .connectTimeout(180, TimeUnit.SECONDS)
        .readTimeout(180, TimeUnit.SECONDS)
        .writeTimeout(180, TimeUnit.SECONDS)
        .build()


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