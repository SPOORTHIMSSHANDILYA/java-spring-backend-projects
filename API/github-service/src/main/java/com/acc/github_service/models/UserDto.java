package com.acc.github_service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String login;
    private long id;
    private String name;
    @JsonProperty("public_repos")
    private long publicRepos;
    private long followers;
    private long following;
}