package com.acc.libraryManagement.controllers;

import com.acc.common_lib.models.Response;
import com.acc.libraryManagement.models.BookRequestDTO;
import com.acc.libraryManagement.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public Response getAllBooks(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy,
                                @RequestParam(defaultValue = "asc") String direction){
        return new Response(HttpStatus.OK,"Success",bookService.getAllBooks(page,size,sortBy,direction),"Successfully retrieved all Books");
    }

    @GetMapping("/books/{id}")
    public Response getBookById(@PathVariable int id){
        return new Response(HttpStatus.OK,"Success",bookService.findBookById(id),"Successfully retrieved book");
    }

    @GetMapping("/books/search")
    public Response searchBooks(@RequestParam(required = false) String title,
                                @RequestParam(required = false) String genre,
                                @RequestParam(required = false) int authorId){
        return new Response(HttpStatus.OK,"Success",bookService.searchBooks(title,genre,authorId),"Successfully searched all Books");
    }

    @GetMapping("/books/available")
    public Response getAvailableBooks(){
        return new Response(HttpStatus.OK,"Success",bookService.availableBooks(),"Successfully retrieved all available Books");
    }

    @PostMapping("/books")
    public Response saveBook(@Valid @RequestBody BookRequestDTO bookRequest){
        return new Response(HttpStatus.OK,"Success",bookService.saveBook(bookRequest),"Successfully added Book");
    }

    @PutMapping("/books/{id}")
    public Response updateBook(@Valid @RequestBody BookRequestDTO bookRequest, @PathVariable int id){
        return new Response(HttpStatus.OK,"Success",bookService.updateBook(bookRequest, id),"Successfully updated Book with id: "+ id);
    }

    @DeleteMapping("/books/{id}")
    public Response deleteBook(@PathVariable int id){
        return new Response(HttpStatus.OK,"Success",bookService.deleteBook(id),"Successfully deleted Book with id : "+ id);
    }
}