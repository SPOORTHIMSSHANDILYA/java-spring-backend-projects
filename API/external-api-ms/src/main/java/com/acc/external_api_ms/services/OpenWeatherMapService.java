package com.acc.external_api_ms.services;

import com.acc.external_api_ms.models.openWeatherMap.WeatherResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Service
@Slf4j
public class OpenWeatherMapService {
    @Value("${api.weatherApi.baseUrl}")
    private String baseUrl;

    @Value("${api.weatherApi.apiKey}")
    private String apiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public OpenWeatherMapService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public WeatherResponseDto getCurrentWeatherInfo(double lat, double lon){
        URI finalUrl = UriComponentsBuilder.fromUriString(baseUrl + "/weather")
                .queryParam("lat",lat)
                .queryParam("lon",lon)
                .queryParam("appid",apiKey)
                .build().toUri();

        log.info("finalUrl: {}", finalUrl);
        return restTemplate.getForObject(finalUrl, WeatherResponseDto.class);
    }
}
