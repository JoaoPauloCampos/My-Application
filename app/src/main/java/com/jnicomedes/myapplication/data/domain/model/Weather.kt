package com.jnicomedes.myapplication.data.domain.model

data class Weather(
    val title: String,
    val id: Long,
    val minTemp: String,
    val maxTemp: String,
    val currentTemp: String,
    val condition: String,
    val image: String
)
