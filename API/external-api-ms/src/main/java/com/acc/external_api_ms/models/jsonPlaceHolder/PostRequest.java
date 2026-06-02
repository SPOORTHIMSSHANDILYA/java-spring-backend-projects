package com.acc.external_api_ms.models.jsonPlaceHolder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostRequest {
    @NotNull(message = "userId cannot be null")
    private Long userId;

    @NotBlank(message = "title cannot be blank")
    private String title;

    @NotBlank(message = "body cannot be blank")
    private String body;
}