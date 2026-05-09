package com.acc.libraryManagement.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({
        "id","title","isbn","genre","publishedYear","totalCopies","availableCopies","createdAt"
})
public class BooksResponseDTO {
    private Long id;
    private String title;
    private String isbn;
    private String genre;
    private Integer publishedYear;
    private Integer totalCopies;
    private Integer availableCopies;
    private LocalDateTime createdAt;

    private String authorFirstName;
    private String authorLastName;
}
