package com.acc.external_api_ms.models.jsonPlaceHolder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentsDto {
    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;
}
