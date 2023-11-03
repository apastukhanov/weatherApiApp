## Weather Forecast Reactive API

### Overview
The Weather Forecast Reactive API is a simple, efficient, and streamlined service developed using Kotlin and Spring Boot's reactive programming paradigm. This project fetches and transforms weather forecast data from the National Weather Service to present it in an easily digestible format for end-users.

### Key Features

- **Reactive REST Endpoint**: The API provides a REST endpoint that fetches weather data reactively, ensuring non-blocking operations and optimal performance.
  
- **Data Transformation**: Fetched data is transformed using reactive constructs like Mono and Flux, ensuring efficient data handling in line with the reactive paradigm.
  
- **Simplified Data Structure**: The API returns a tailored structure for daily weather forecasts, which includes:
  - `Day Name`
  - `High Temperature (in Celsius)`
  - `Short Weather Description (Forecast Blurp)`

### Technologies Used

- **Language**: Kotlin
- **Framework**: Spring Boot with WebFlux
- **Design Pattern**: MVC (Model-View-Controller) pattern for a structured codebase.
- **External API**: [National Weather Service's API endpoint](https://api.weather.gov/gridpoints/MLB/33,70/forecast)

### Usage
Upon making a GET request to the API's designated endpoint, users will receive the weather forecast for the current day, formatted as described. Whether you're a developer integrating this data or an end-user seeking weather updates, the Weather Forecast Reactive API caters to your needs.
