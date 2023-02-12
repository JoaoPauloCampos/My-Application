package com.jnicomedes.myapplication.data.domain.mapper

import com.jnicomedes.myapplication.data.domain.model.Weather
import com.jnicomedes.myapplication.data.remote.model.WeatherContentRemote

fun WeatherContentRemote.toDomain(): Weather {
    val consolidatedWeather = consolidatedWeather.first()
    return Weather(
        title = title,
        id = woeId,
        minTemp = "${consolidatedWeather.minTemp.toInt()}°",
        maxTemp = "${consolidatedWeather.maxTemp.toInt()}°",
        currentTemp = "${consolidatedWeather.theTemp.toInt()}°",
        condition = consolidatedWeather.weatherStateName,
        image = consolidatedWeather.weatherStateAbbr.weatherImageFromPlaceholder()
    )
}

fun String.weatherImageFromPlaceholder() = "https://cdn.faire.com/static/mobile-take-home/icons/${this}.png"