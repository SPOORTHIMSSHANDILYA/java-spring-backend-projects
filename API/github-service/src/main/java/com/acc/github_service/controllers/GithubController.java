package com.acc.github_service.controllers;

import com.acc.common_lib.models.Response;
import com.acc.github_service.models.IssuesRequestDto;
import com.acc.github_service.models.RepoRequestDto;
import com.acc.github_service.service.GithubService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/github")
public class GithubController {
    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/user/{userName}")
    public Response getUser(@PathVariable String userName, @RequestParam String token){
        return new Response(HttpStatus.OK,"Success",githubService.getUserByUserName(userName, token),
                "Successfully retrieved user info from Github API");

    }

    @GetMapping("/user/{userName}/repos")
    public Response getRepository(@PathVariable String userName, @RequestParam String token){
        return new Response(HttpStatus.OK,"Success",githubService.getReposByUserName(userName, token),
                "Successfully retrieved repository info from Github API");

    }

    @GetMapping("/repos/{owner}/{repo}")
    public Response getRepoDetails(@PathVariable String owner, @PathVariable String repo, @RequestParam String token){
        return new Response(HttpStatus.OK,"Success",githubService.getRepoDetails(owner, repo, token),
                "Successfully retrieved repository details from Github API");

    }

    @GetMapping("/repos/{repo}/search")
    public Response searchRepo(@PathVariable String repo, @RequestParam String token){
        return new Response(HttpStatus.OK,"Success",githubService.searchRepo(repo, token),
                "Successfully searched repo info from Github API");

    }


    @GetMapping("/repos/{owner}/{repo}/contributors")
    public Response getContributors(@PathVariable String owner, @PathVariable String repo, @RequestParam String token){
        return new Response(HttpStatus.OK,"Success",githubService.getContributors(owner, repo, token),
                "Successfully retrieved contributor info from Github API");

    }

    @GetMapping("/repos/{owner}/{repo}/languages")
    public Response getLanguages(@PathVariable String owner, @PathVariable String repo, @RequestParam String token){
        return new Response(HttpStatus.OK,"Success",githubService.getLanguages(owner, repo, token),
                "Successfully retrieved language details from Github API");

    }

    @GetMapping("/repos/{owner}/{repo}/commits")
    public Response getCommits(@PathVariable String owner, @PathVariable String repo, @RequestParam String token){
        return new Response(HttpStatus.OK,"Success",githubService.getCommits(owner, repo, token),
                "Successfully retrieved commit details from Github API");

    }


    @PostMapping("/repo/create")
    public Response createRepo(@RequestBody RepoRequestDto repoRequestDto, @RequestParam String token){
        return new Response(HttpStatus.OK,"Success",githubService.createRepo(repoRequestDto, token),
                "Successfully created new repo via Github API");

    }


    @PostMapping("/repos/{owner}/{repo}/issues")
    public Response createIssue(@PathVariable String owner, @PathVariable String repo, @RequestBody IssuesRequestDto issuesRequestDto,
                                @RequestParam String token){
        return new Response(HttpStatus.OK,"Success",githubService.createIssue(owner, repo,issuesRequestDto, token),
                "Successfully created new issue via Github API");

    }
}
