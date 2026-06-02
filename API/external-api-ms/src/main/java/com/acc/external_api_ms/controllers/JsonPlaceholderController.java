package com.acc.external_api_ms.controllers;

import com.acc.common_lib.models.Response;
import com.acc.external_api_ms.models.jsonPlaceHolder.PostRequest;
import com.acc.external_api_ms.services.JsonPlaceholderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/json-placeholder")
@Slf4j
public class JsonPlaceholderController {
    private final JsonPlaceholderService jsonPlaceholderService;

    public JsonPlaceholderController(JsonPlaceholderService jsonPlaceholderService) {
        this.jsonPlaceholderService = jsonPlaceholderService;
    }

    // ===== USERS =====
    @GetMapping("/users")
    public Response getAllUsers(){
        log.info("Request received: GET /api/json-placeholder/users");
        return new Response(HttpStatus.OK,"Success",jsonPlaceholderService.getAllUsers(),
                "Successfully retrieved users from JSONPlaceholder API");
    }

    @GetMapping("/users/{id}")
    public Response getUserFromId(@PathVariable Long id){
        log.info("Request received: GET /api/json-placeholder/users/{} with id={}", id, id);
        return new Response(HttpStatus.OK,"Success",jsonPlaceholderService.getUserFromId(id),
                "Successfully retrieved user from JSONPlaceholder API");
    }

    // ===== POSTS =====
    @GetMapping("/posts")
    public Response getAllPosts(){
        log.info("Request received: GET /api/json-placeholder/posts");
        return new Response(HttpStatus.OK,"Success",jsonPlaceholderService.getAllPosts(),
                "Successfully retrieved posts from JSONPlaceholder API");
    }

    @GetMapping("/posts/{id}")
    public Response getPostFromId(@PathVariable Long id){
        log.info("Request received: GET /api/json-placeholder/posts/{} with id={}", id, id);
        return new Response(HttpStatus.OK,"Success",jsonPlaceholderService.getPostFromId(id),
                "Successfully retrieved post from JSONPlaceholder API");
    }

    @PostMapping("/posts")
    public Response createPost(@Valid @RequestBody PostRequest postRequest){
        log.info("Request received: POST /api/json-placeholder/posts with userId={}, title={}",
            postRequest.getUserId(), postRequest.getTitle());
        return new Response(HttpStatus.CREATED,"Success",jsonPlaceholderService.createPost(postRequest),
                "Successfully created post in JSONPlaceholder API");
    }

    @PutMapping("/posts/{id}")
    public Response updatePost(@Valid @RequestBody PostRequest postRequest, @PathVariable Long id){
        log.info("Request received: PUT /api/json-placeholder/posts/{} with id={}, userId={}",
            id, id, postRequest.getUserId());
        return new Response(HttpStatus.OK,"Success",jsonPlaceholderService.updatePost(postRequest,id),
                "Successfully updated post in JSONPlaceholder API");
    }

    @PatchMapping("/posts/{id}")
    public Response patchPost(@RequestBody PostRequest postRequest, @PathVariable Long id){
        log.info("Request received: PATCH /api/json-placeholder/posts/{} with id={}", id, id);
        return new Response(HttpStatus.OK,"Success",jsonPlaceholderService.patchPost(postRequest,id),
                "Successfully patched post in JSONPlaceholder API");
    }

    @DeleteMapping("/posts/{id}")
    public Response deletePost(@PathVariable Long id){
        log.info("Request received: DELETE /api/json-placeholder/posts/{} with id={}", id, id);
        return new Response(HttpStatus.OK,"Success",jsonPlaceholderService.deletePost(id),
                "Successfully deleted post from JSONPlaceholder API");
    }

    // ===== COMMENTS =====
    @GetMapping("/comments")
    public Response getAllComments(){
        log.info("Request received: GET /api/json-placeholder/comments");
        return new Response(HttpStatus.OK,"Success",jsonPlaceholderService.getAllComments(),
                "Successfully retrieved comments from JSONPlaceholder API");
    }

    @GetMapping("/comments/{postId}")
    public Response getCommentsFromPostId(@PathVariable Long postId){
        log.info("Request received: GET /api/json-placeholder/comments/{} with postId={}", postId, postId);
        return new Response(HttpStatus.OK,"Success",jsonPlaceholderService.getCommentsFromPostId(postId),
                "Successfully retrieved comments for postId from JSONPlaceholder API");
    }
}
