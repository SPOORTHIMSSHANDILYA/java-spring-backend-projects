package com.acc.libraryManagement.controllers;

import com.acc.common_lib.models.Response;
import com.acc.libraryManagement.services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BorrowController {
    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping("/borrows")
    public Response getAllBorrows(){
        try{
            return new Response(HttpStatus.OK,"Success",borrowService.getAllBorrowRecords(),"Successfully retrieved all borrow records");
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR,"Error",null,"Failed to retrieve borrow records");
        }
    }

    @GetMapping("/borrows/overdue")
    public Response getAllOverdueBorrows(){
        try{
            return new Response(HttpStatus.OK,"Success",borrowService.getAllOverDueBorrowRecords(),"Successfully retrieved all overdue borrow records");
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR,"Error",null,"Failed to retrieve overdue borrow records");
        }
    }


    @GetMapping("/borrows/{id}")
    public Response getAllOverdueBorrows(@PathVariable Long id){
        try{
            return new Response(HttpStatus.OK,"Success",borrowService.getBorrowById(id),"Successfully retrieved borrow record with id : "+id);
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR,"Error",null,"Failed to retrieve borrow record with id : "+id);
        }
    }
}
