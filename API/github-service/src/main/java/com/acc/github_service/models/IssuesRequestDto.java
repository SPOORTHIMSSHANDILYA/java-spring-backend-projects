package com.acc.github_service.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IssuesRequestDto {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Body is required")
    private String body;
}
