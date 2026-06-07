package com.acc.github_service.service;

import com.acc.github_service.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;

import java.util.List;

@Service
public class GithubService {

    @Value("${api.githubApi.baseUrl}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public GithubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public HttpHeaders getHeaders(String token){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/vnd.github+json");
        httpHeaders.set("X-GitHub-Api-Version", "2022-11-28");
        httpHeaders.set("Authorization", "Bearer " + token);
        return httpHeaders;
    }

    public UserDto getUserByUserName(String name, String token){
        String finalUrl = baseUrl + "/users/" + name;

        HttpEntity<Void> request = new HttpEntity<>(getHeaders(token));
        ResponseEntity<UserDto> response = restTemplate.exchange(finalUrl, HttpMethod.GET, request, UserDto.class);
        return response.getBody();
    }

    public List<RepoDto> getReposByUserName(String name, String token){
        String finalUrl = baseUrl + "/users/" + name + "/repos";

        HttpEntity<Void> request = new HttpEntity<>(getHeaders(token));
        ResponseEntity<RepoDto[]> response = restTemplate.exchange(finalUrl, HttpMethod.GET, request, RepoDto[].class);
        return List.of(response.getBody());
    }

    public RepoDto getRepoDetails(String owner, String repo, String token){
        String finalUrl = baseUrl + "/repos/" + owner + "/" + repo;

        HttpEntity<Void> request = new HttpEntity<>(getHeaders(token));
        ResponseEntity<RepoDto> response = restTemplate.exchange(finalUrl, HttpMethod.GET, request, RepoDto.class);
        return response.getBody();
    }

    public JsonNode searchRepo(String repo, String token){
        String finalUrl = baseUrl + "/search/repositories?q=" + repo;
        HttpEntity<Void> request = new HttpEntity<>(getHeaders(token));
        ResponseEntity<JsonNode> response = restTemplate.exchange(finalUrl,HttpMethod.GET,request,JsonNode.class);
        return response.getBody();
    }

    public List<ContributorDto> getContributors(String owner, String repo, String token){
        String finalUrl = baseUrl + "/repos/" + owner + "/" + repo + "/contributors";
        HttpEntity<Void> request = new HttpEntity<>(getHeaders(token));
        ResponseEntity<ContributorDto[]> response = restTemplate.exchange(finalUrl,HttpMethod.GET,request,ContributorDto[].class);
        return List.of(response.getBody());
    }

    public JsonNode getLanguages(String owner, String repo, String token){
        String finalUrl = baseUrl + "/repos/" + owner + "/" + repo + "/languages";
        HttpEntity<Void> request = new HttpEntity<>(getHeaders(token));
        ResponseEntity<JsonNode> response = restTemplate.exchange(finalUrl,HttpMethod.GET,request,JsonNode.class);
        return response.getBody();
    }

    public JsonNode getCommits(String owner, String repo, String token){
        String finalUrl = baseUrl + "/repos/" + owner + "/" + repo + "/commits";
        HttpEntity<Void> request = new HttpEntity<>(getHeaders(token));
        ResponseEntity<JsonNode> response = restTemplate.exchange(finalUrl,HttpMethod.GET,request,JsonNode.class);
        return response.getBody();
    }

    public JsonNode createRepo(RepoRequestDto repoRequestDto , String token){
        String finalUrl = baseUrl + "/user/repos";
        HttpEntity<RepoRequestDto> request = new HttpEntity<>(repoRequestDto,getHeaders(token));
        ResponseEntity<JsonNode> response = restTemplate.exchange(finalUrl,HttpMethod.POST,request,JsonNode.class);
        return response.getBody();
    }

    public JsonNode createIssue(String owner, String repo, IssuesRequestDto issuesRequestDto , String token){
        String finalUrl = baseUrl + "/repos/" + owner + "/" + repo + "/issues";
        HttpEntity<IssuesRequestDto> request = new HttpEntity<>(issuesRequestDto,getHeaders(token));
        ResponseEntity<JsonNode> response = restTemplate.exchange(finalUrl,HttpMethod.POST,request,JsonNode.class);
        return response.getBody();
    }

}
