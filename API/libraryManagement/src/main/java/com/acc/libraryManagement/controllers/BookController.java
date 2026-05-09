package com.acc.libraryManagement.controllers;

import com.acc.common_lib.models.ErrorResponse;
import com.acc.common_lib.models.Response;
import com.acc.libraryManagement.models.BookRequestDTO;
import com.acc.libraryManagement.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    @Autowired
    public  BookController(BookService bookService){
        this.bookService = bookService;
    }


    @GetMapping("/books")
    public Response getAllBooks(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy,
                                @RequestParam(defaultValue = "asc") String direction){
        try{
            return new Response(HttpStatus.OK,"Success",bookService.getAllBooks(page,size,sortBy,direction),"Successfully retrieved all Books");
        }
        catch(Exception ex){
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error in getting response",ex.getMessage());
        }
    }

    @GetMapping("/books/{id}")
    public Response getAllBooks(@PathVariable int id){
        try{
            return new Response(HttpStatus.OK,"Success",bookService.findBookById(id),"Successfully retrieved all Books");
        }
        catch(Exception ex){
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error in getting response",ex.getMessage());
        }
    }


    @GetMapping("/books/search")
    public Response getAllBooks(@RequestParam(required = false) String title,
                                @RequestParam(required = false) String genre,
                                @RequestParam(required = false) int authorId){
        try{
            return new Response(HttpStatus.OK,"Success",bookService.searchBooks(title,genre,authorId),"Successfully searched all Books");
        }
        catch(Exception ex){
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error in getting response",ex.getMessage());
        }
    }

    @GetMapping("/books/available")
    public Response getAllAvailableBooks(){
        try{
            return new Response(HttpStatus.OK,"Success",bookService.availableBooks(),"Successfully searched all Books");
        }
        catch(Exception ex){
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error in getting response",ex.getMessage());
        }
    }

    @PostMapping("/books")
    public Response saveBooks(@RequestBody BookRequestDTO bookRequest){
        try{
            return new Response(HttpStatus.OK,"Success",bookService.saveBook(bookRequest),"Successfully added Book");
        }
        catch(Exception ex){
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error in getting response",ex.getMessage());
        }
    }

    @PutMapping("/books/{id}")
    public Response updateBooks(@RequestBody BookRequestDTO bookRequest, @PathVariable int id){
        try{
            return new Response(HttpStatus.OK,"Success",bookService.updateBook(bookRequest, id),"Successfully updated Book with id: "+ id);
        }
        catch(Exception ex){
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error in getting response",ex.getMessage());
        }
    }


    @DeleteMapping("/books/{id}")
    public Response deleteBooks(@PathVariable int id){
        try{
            return new Response(HttpStatus.OK,"Success",bookService.deleteBook(id),"Successfully deleted Book with id : "+ id);
        }
        catch(Exception ex){
            return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error in getting response",ex.getMessage());
        }
    }
}