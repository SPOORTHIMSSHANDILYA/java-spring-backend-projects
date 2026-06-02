package com.acc.external_api_ms.models.openWeatherMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponseDto {
    private WeatherCoordDto coord;
    private List<WeatherConditionDto> weather;
    private String base;
    private WeatherDataDto main;
    private long visibility;
    private WeatherWindDto wind;
    private WeatherCloudsDto clouds;
    private long dt;
    private WeatherSysDto sys;
    private long timezone;
    private long id;
    private String name;
    private int cod;
}
