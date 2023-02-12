package com.jnicomedes.myapplication.data.remote.model

import com.squareup.moshi.Json

data class ConsolidatedWeatherRemote(
    val id: Long,

    @field:Json(name = "weather_state_name")
    val weatherStateName: String,

    @field:Json(name = "weather_state_abbr")
    val weatherStateAbbr: String,

    @field:Json(name = "wind_direction_compass")
    val windDirectionCompass: String,

    val created: String,

    @field:Json(name = "applicable_date")
    val applicableDate: String,

    @field:Json(name = "min_temp")
    val minTemp: Double,

    @field:Json(name = "max_temp")
    val maxTemp: Double,

    @field:Json(name = "the_temp")
    val theTemp: Double,

    @field:Json(name = "wind_speed")
    val windSpeed: Double,

    @field:Json(name = "wind_direction")
    val windDirection: Double,

    @field:Json(name = "air_pressure")
    val airPressure: Double,

    val humidity: Long,
    val visibility: Double,
    val predictability: Long
)
