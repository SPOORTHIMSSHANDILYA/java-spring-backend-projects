package com.acc.libraryManagement.controllers;

import com.acc.common_lib.models.Response;
import com.acc.libraryManagement.models.AuthorRequestDTO;
import com.acc.libraryManagement.services.AuthorService;
import jakarta.validation.Valid;
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
        return new Response(HttpStatus.OK,"Success",authorService.getAllAuthors(),"Successfully retrieved all authors");
    }

    @GetMapping("/authors/{id}")
    public Response getAuthorsById(@PathVariable int id){
        return new Response(HttpStatus.OK,"Success",authorService.getAuthorsById(id),"Successfully retrieved author");
    }

    @GetMapping("/authors/{id}/books")
    public Response getBooksByAuthorId(@PathVariable int id){
        return new Response(HttpStatus.OK,"Success", authorService.getBooksByAuthorId(id),"Successfully retrieved all books");
    }

    @PostMapping("/authors")
    public Response saveAuthor(@Valid @RequestBody AuthorRequestDTO authorRequest){
        return new Response(HttpStatus.OK,"Success",authorService.saveAuthor(authorRequest),"Successfully saved author");
    }

    @PutMapping("/authors/{id}")
    public Response updateAuthor(@PathVariable int id, @Valid @RequestBody AuthorRequestDTO authorRequest){
        return new Response(HttpStatus.OK,"Success",authorService.updateAuthor(id,authorRequest),"Successfully updated existing Author");
    }

    @DeleteMapping("/authors/{id}")
    public Response deleteAuthor(@PathVariable int id){
        return new Response(HttpStatus.OK,"Success",authorService.deleteAuthor(id),"Successfully deleted existing Author");
    }
}
