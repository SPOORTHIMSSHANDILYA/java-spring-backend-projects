package com.acc.github_service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepoDto {
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private String language;
    @JsonProperty("stargazers_count")
    private long stargazersCount;
    @JsonProperty("forks_count")
    private long forksCount;
}