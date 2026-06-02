package com.acc.external_api_ms.models.openWeatherMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherCoordDto {
    private double lat;
    private double lon;
}
