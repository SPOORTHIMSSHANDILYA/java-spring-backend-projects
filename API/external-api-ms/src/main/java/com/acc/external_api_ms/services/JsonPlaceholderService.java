package com.acc.external_api_ms.services;

import com.acc.external_api_ms.models.jsonPlaceHolder.CommentsDto;
import com.acc.external_api_ms.models.jsonPlaceHolder.PostRequest;
import com.acc.external_api_ms.models.jsonPlaceHolder.PostsDto;
import com.acc.external_api_ms.models.jsonPlaceHolder.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
@Slf4j
public class JsonPlaceholderService {

    @Value("${api.jsonPlaceholder.baseUrl}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public JsonPlaceholderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<UserDto> getAllUsers(){
        String finalUrl = baseUrl + "/users";
        UserDto[] users = restTemplate.getForObject(finalUrl, UserDto[].class);
        if(users == null){
            log.warn("No users found from JSONPlaceholder API");
            return List.of();
        }
        log.info("Successfully retrieved {} users from JSONPlaceholder API", users.length);
        return List.of(users);
    }

    public List<PostsDto> getAllPosts(){
        String finalUrl = baseUrl + "/posts";
        PostsDto[] posts = restTemplate.getForObject(finalUrl,PostsDto[].class);
        if(posts == null){
            log.warn("No posts found from JSONPlaceholder API");
            return List.of();
        }
        log.info("Successfully retrieved {} posts from JSONPlaceholder API", posts.length);
        return List.of(posts);
    }


    public PostsDto getPostFromId(Long id){
        String finalUrl = baseUrl + "/posts/{id}";
        PostsDto post = restTemplate.getForObject(finalUrl,PostsDto.class,id);
        if(post == null){
            log.warn("Post with id {} not found from JSONPlaceholder API", id);
            return null;
        }
        log.info("Successfully retrieved post with id {} from JSONPlaceholder API", id);
        return post;
    }

    public UserDto getUserFromId(Long id){
        String finalUrl = baseUrl + "/users/{id}";
        ResponseEntity<UserDto> user = restTemplate.getForEntity(finalUrl,UserDto.class,id);
        if(user.getStatusCode().is2xxSuccessful() && user.getBody() != null){
            log.info("Successfully retrieved user with id {} from JSONPlaceholder API", id);
            return user.getBody();
        }
        log.warn("User with id {} not found from JSONPlaceholder API", id);
        return null;
    }

    public List<CommentsDto> getAllComments(){
        String finalUrl = baseUrl + "/comments";
        CommentsDto[] comments = restTemplate.getForObject(finalUrl,CommentsDto[].class);
        if(comments == null){
            log.warn("No comments found from JSONPlaceholder API");
            return List.of();
        }
        log.info("Successfully retrieved {} comments from JSONPlaceholder API", comments.length);
        return List.of(comments);
    }

    public List<CommentsDto> getCommentsFromPostId(Long postId){
        String finalUrl = baseUrl + "/posts/{postId}/comments";
        CommentsDto[] comments = restTemplate.getForObject(finalUrl,CommentsDto[].class,postId);
        if(comments == null){
            log.warn("No comments found for postId {} from JSONPlaceholder API", postId);
            return List.of();
        }
        log.info("Successfully retrieved {} comments for postId {} from JSONPlaceholder API", comments.length, postId);
        return List.of(comments);
    }

    public List<CommentsDto> getCommentsFromPostId2(Long postId){
        String finalUrl = baseUrl + "/comments?postId={postId}";
        CommentsDto[] comments = restTemplate.getForObject(finalUrl,CommentsDto[].class,postId);
        if(comments == null){
            log.warn("No comments found for  postId {} from JSONPlaceholder API", postId);
            return List.of();
        }
        log.info("Successfully retrieved {} comments  for postId {} from JSONPlaceholder API", comments.length, postId);
        return List.of(comments);
    }

    public PostsDto createPost(PostRequest postRequest){
        String finalUrl = baseUrl + "/posts";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PostRequest> request = new HttpEntity<>(postRequest, headers);

        ResponseEntity<PostsDto> post = restTemplate.postForEntity(finalUrl,request,PostsDto.class);
        if(post.getStatusCode().is2xxSuccessful() && post.getBody() != null){
            log.info("Successfully created post with id {} in JSONPlaceholder API", post.getBody().getId());
            return post.getBody();
        }
        log.error("Failed to create post in JSONPlaceholder API");
        return null;
    }

    public PostsDto updatePost(PostRequest postRequest, Long id){
        String finalUrl = baseUrl + "/posts/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((MediaType.APPLICATION_JSON));

        HttpEntity<PostRequest> request = new HttpEntity<>(postRequest,headers);

        ResponseEntity<PostsDto> response = restTemplate.exchange(finalUrl, HttpMethod.PUT,request,PostsDto.class,id);

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            log.info("Successfully updated post with id {} in JSONPlaceholder API", id);
            return response.getBody();
        }
        log.error("Failed to update post with id {} in JSONPlaceholder API", id);
        return null;
    }

    public PostsDto patchPost(PostRequest postRequest, Long id){
        String finalUrl = baseUrl + "/posts/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((MediaType.APPLICATION_JSON));

        HttpEntity<PostRequest> request = new HttpEntity<>(postRequest,headers);

        ResponseEntity<PostsDto> response = restTemplate.exchange(finalUrl, HttpMethod.PATCH,request,PostsDto.class,id);

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            log.info("Successfully patched post with id {} in JSONPlaceholder API", id);
            return response.getBody();
        }
        log.error("Failed to patch post with id {} in JSONPlaceholder API", id);
        return null;
    }


    public String deletePost(Long id){
        String finalUrl = baseUrl + "/posts/{id}";

        ResponseEntity<PostsDto> response = restTemplate.exchange(finalUrl, HttpMethod.DELETE,null,PostsDto.class,id);

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
            log.info("Successfully deleted post with id {} from JSONPlaceholder API", id);
            return "Successfully deleted post with id "+ id + " from JSONPlaceholder API";
        }
        log.error("Failed to delete post with id {} from JSONPlaceholder API", id);
        return null;
    }
}