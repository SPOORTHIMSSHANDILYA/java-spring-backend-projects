package com.acc.libraryManagement.controllers;

import com.acc.common_lib.models.ErrorResponse;
import com.acc.common_lib.models.Response;
import com.acc.libraryManagement.models.AuthorRequestDTO;
import com.acc.libraryManagement.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public Response getAllAuthors(){
        try{
            return new Response(HttpStatus.OK,"Success",authorService.getAllAuthors(),"Successfully retrieved all authors");
        }
        catch(Exception ex){
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error in getting response",ex.getMessage());
        }
    }

    @GetMapping("/authors/{id}")
    public Response getAuthorsById(@PathVariable int id){
        try{
            return new Response(HttpStatus.OK,"Success",authorService.getAuthorsById(id),"Successfully retrieved authors");
        }
        catch(Exception ex){
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error in getting response",ex.getMessage());
        }
    }

    @GetMapping("/authors/{id}/books")
    public Response getBooksByAuthorId(@PathVariable int id){
        try{
            return new Response(HttpStatus.OK,"Success", authorService.getBooksByAuthorId(id),"Successfully retrieved all books");
        }
        catch(Exception ex){
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error in getting response",ex.getMessage());
        }
    }

    @PostMapping("/authors")
    public Response saveAuthor(@RequestBody AuthorRequestDTO authorRequest){
        try{
            return new Response(HttpStatus.OK,"Success",authorService.saveAuthor(authorRequest),"Successfully saved author");
        }
        catch(Exception ex){
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error in getting response",ex.getMessage());
        }
    }

    @PutMapping("/authors/{id}")
    public Response updateAuthor(@PathVariable int id, @RequestBody AuthorRequestDTO authorRequest){
        try{
            return new Response(HttpStatus.OK,"Success",authorService.updateAuthor(id,authorRequest),"Successfully updated existing Author");
        }
        catch(Exception ex){
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error in getting response",ex.getMessage());
        }
    }

    @DeleteMapping("/authors/{id}")
    public Response deleteAuthor(@PathVariable int id){
        try{
            return new Response(HttpStatus.OK,"Success",authorService.deleteAuthor(id),"Successfully deleted existing Author");
        }
        catch(Exception ex){
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error in getting response",ex.getMessage());
        }
    }
}
