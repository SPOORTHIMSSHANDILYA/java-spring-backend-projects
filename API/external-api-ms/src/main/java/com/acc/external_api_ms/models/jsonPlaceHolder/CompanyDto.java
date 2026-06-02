package com.acc.external_api_ms.models.jsonPlaceHolder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDto {
    private String name;
    private String catchPhrase;
    private String bs;
}
