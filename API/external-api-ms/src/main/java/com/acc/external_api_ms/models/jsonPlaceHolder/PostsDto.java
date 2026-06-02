package com.acc.external_api_ms.models.jsonPlaceHolder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostsDto {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}
