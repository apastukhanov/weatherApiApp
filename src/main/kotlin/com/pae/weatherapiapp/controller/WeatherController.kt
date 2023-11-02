package com.pae.weatherapiapp.controller

import com.pae.weatherapiapp.service.WeatherService
import com.pae.weatherapiapp.model.ForecastResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.time.LocalDate

@RestController
@RequestMapping
class WeatherController(
    private val weatherService: WeatherService
) {

    @GetMapping(
        value = ["/forecast/daily-max"],
        produces = ["application/json"])
    fun getDailyMaxForecast(): Mono<ForecastResponse> {
        return weatherService.get(LocalDate.now())
    }

    @GetMapping(
        value = ["/forecast/weekly-all"],
        produces = ["application/json"])
    fun getWeeklyAllForecast(): Mono<ForecastResponse> {
        return weatherService.list()
    }
}
