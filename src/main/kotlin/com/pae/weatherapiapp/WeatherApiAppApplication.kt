package com.pae.weatherapiapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeatherApiAppApplication

fun main(args: Array<String>) {
    runApplication<WeatherApiAppApplication>(*args)
}
