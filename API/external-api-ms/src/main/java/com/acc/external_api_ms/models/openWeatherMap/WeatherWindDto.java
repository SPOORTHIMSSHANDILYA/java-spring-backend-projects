package com.acc.external_api_ms.models.openWeatherMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherWindDto {
    private double speed;
    private long deg;
    private double gust;
}