package com.pae.weatherapiapp.model

data class ApiForecastResponse(
    val properties: Properties
)

data class Properties(
    val periods: List<Period>
)

data class Period(
    val name: String,
    val startTime: String,
    val endTime: String,
    val temperature: Int,
    val isDay: Boolean,
    val shortForecast: String
)
