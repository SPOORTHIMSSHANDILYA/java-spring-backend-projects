package com.acc.external_api_ms.models.jsonPlaceHolder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoDto {
    private String lat;
    private String lng;
}