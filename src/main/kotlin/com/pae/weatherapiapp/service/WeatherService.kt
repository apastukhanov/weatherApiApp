package com.pae.weatherapiapp.service

import com.pae.weatherapiapp.model.DailyForecast
import com.pae.weatherapiapp.model.ForecastResponse
import com.pae.weatherapiapp.model.ApiForecastResponse
import com.pae.weatherapiapp.model.Period
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@Service
class WeatherService(
    private val webClient: WebClient = WebClient.create(BASE_URL)
) {

    companion object {
        const val BASE_URL = "https://api.weather.gov"
        const val DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX"
        val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)

        enum class EndPoints(val url: String) {
            FORECAST("/gridpoints/MLB/33,70/forecast");
        }
    }

    fun get(date: LocalDate): Mono<ForecastResponse> {
        return getForecasts { periods ->
            periods
                .filter { LocalDate.parse(it.startTime, formatter) == date }
                .maxByOrNull { it.temperature }
                ?.let { listOf(it) } ?: emptyList()
        }
    }

    fun list(): Mono<ForecastResponse> {
        return getForecasts { it }
    }

    private fun getForecasts(filter: (List<Period>) -> List<Period>): Mono<ForecastResponse> {
        return requestApi(EndPoints.FORECAST.url, ApiForecastResponse::class.java)
            .handle { response, sink ->
                response.properties.periods.firstOrNull()?.let {
                    sink.next(
                        ForecastResponse(
                            filter(response.properties.periods).map { period ->
                                DailyForecast(
                                    LocalDateTime.parse(period.startTime, formatter).dayOfWeek
                                        .getDisplayName(TextStyle.FULL, Locale.getDefault()),
                                    ((period.temperature - 32) * 5 / 9).toDouble(),
                                    period.shortForecast
                                )
                            }
                        )
                    )
                } ?: sink.error(RuntimeException("No daily forecast found"))
            }
    }

    private fun <T> requestApi(url: String, responseType: Class<T>): Mono<T> {
        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(responseType)
    }
}
