package com.acc.libraryManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {
    private String title;
    private String isbn;
    private String genre;
    private Integer publishedYear;
    private Integer totalCopies;
    private Integer availableCopies;
    private String authorFirstName;
    private String authorLastName;
}
