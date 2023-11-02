package com.pae.weatherapiapp.model

data class DailyForecast(
    val dayName: String,
    val tempHighCelsius: Double,
    val forecastBlurp: String
)

data class ForecastResponse(
    val daily: List<DailyForecast>
)
