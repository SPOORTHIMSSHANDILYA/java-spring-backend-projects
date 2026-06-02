package com.acc.external_api_ms.controllers;

import com.acc.common_lib.models.Response;
import com.acc.external_api_ms.services.OpenWeatherMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class OpenWeatherMapController {

    private final OpenWeatherMapService openWeatherMapService;

    @Autowired
    public OpenWeatherMapController(OpenWeatherMapService openWeatherMapService) {
        this.openWeatherMapService = openWeatherMapService;
    }

    @GetMapping("/current")
    public Response getCurrentInfo(@RequestParam double lat, @RequestParam double lon){
        return new Response(HttpStatus.OK,"Success",openWeatherMapService.getCurrentWeatherInfo(lat, lon),
                "Successfully retrieved current weather information from OpenWeatherMap API");
    }
}
