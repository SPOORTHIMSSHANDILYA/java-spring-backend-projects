package com.acc.libraryManagement.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({
        "id",
        "firstName",
        "lastName",
        "email",
        "bio",
        "nationality",
        "createdAt"
})
public class AuthorResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private String nationality;
    private LocalDateTime createdAt;
}
