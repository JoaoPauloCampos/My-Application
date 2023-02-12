package com.jnicomedes.myapplication.data.remote.model

import com.squareup.moshi.Json

data class WeatherContentRemote(
    @field:Json(name = "consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeatherRemote>,

    val time: String,

    @field:Json(name = "sun_rise")
    val sunRise: String,

    @field:Json(name = "sun_set")
    val sunSet: String,

    @field:Json(name = "timezone_name")
    val timezoneName: String,

    val parent: Parent,
    val sources: List<Source>,

    @field:Json(name = "title")
    val title: String,

    @field:Json(name = "location_type")
    val locationType: String,

    @field:Json(name = "woeid")
    val woeId: Long,

    @field:Json(name = "latt_long")
    val latLong: String,

    val timezone: String,
)
