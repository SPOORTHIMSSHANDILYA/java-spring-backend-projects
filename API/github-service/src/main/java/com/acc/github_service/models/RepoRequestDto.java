package com.acc.github_service.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RepoRequestDto {
    @NotBlank(message = "Repository name is required")
    private String name;
    @NotNull(message = "Private field is required")
    private boolean isPrivate;
}