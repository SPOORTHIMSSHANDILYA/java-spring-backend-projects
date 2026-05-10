package com.acc.libraryManagement.controllers;

import com.acc.common_lib.models.Response;
import com.acc.libraryManagement.models.MemberRequestDTO;
import com.acc.libraryManagement.services.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public Response getAllMembers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "id") String sortBy,
                                  @RequestParam(defaultValue = "asc") String direction){
        return new Response(HttpStatus.OK,"Success",memberService.getAllMembersWithPagination(page,size,sortBy,direction),"Successfully retrieved all members");
    }

    @GetMapping("/members/{id}")
    public Response getMembersById(@PathVariable int id){
        return new Response(HttpStatus.OK,"Success",memberService.getMembersById(id),"Successfully retrieved given member");
    }

    @GetMapping("/members/{id}/borrows")
    public Response getMemberBorrowHistory(@PathVariable int id){
        return new Response(HttpStatus.OK,"Success",memberService.getMemberBorrowHistory(id),"Successfully retrieved borrow history of member");
    }

    @PostMapping("/members")
    public Response saveMember(@Valid @RequestBody MemberRequestDTO memberRequest){
        return new Response(HttpStatus.OK,"Success",memberService.saveMember(memberRequest),"Successfully saved member");
    }

    @PutMapping("/members/{id}")
    public Response editMember(@Valid @RequestBody MemberRequestDTO memberRequest,@PathVariable int id){
        return new Response(HttpStatus.OK,"Success",memberService.editMember(memberRequest,id),"Successfully edited member");
    }

    @PatchMapping("/members/{id}/deactivate")
    public Response deactivateMember(@PathVariable int id){
        return new Response(HttpStatus.OK,"Success",memberService.deactivateMember(id),"Successfully deactivated member");
    }
}