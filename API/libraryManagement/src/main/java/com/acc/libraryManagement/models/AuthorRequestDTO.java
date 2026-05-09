package com.acc.libraryManagement.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequestDTO {

    @NotBlank(message = "First Name is required")
    @Size(max = 60)
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Size(max = 60)
    private String lastName;

    @Email(message = "Email is required")
    @Size(max = 120)
    private String email;

    private String bio;

    private String nationality;
}