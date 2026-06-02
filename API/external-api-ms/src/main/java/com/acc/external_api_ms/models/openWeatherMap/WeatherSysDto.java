package com.acc.external_api_ms.models.openWeatherMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherSysDto {
    private int id;
    private long type;
    private String country;
    private long sunrise;
    private long sunset;
}
