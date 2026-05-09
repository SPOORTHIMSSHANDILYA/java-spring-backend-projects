package com.acc.libraryManagement.mapper;

import com.acc.libraryManagement.entities.Book;
import com.acc.libraryManagement.models.BookRequestDTO;
import com.acc.libraryManagement.models.BooksResponseDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookRequestDTO bookRequestDTO);

    BooksResponseDTO toDto(Book book);

    @AfterMapping
    default void fillAuthorFields(Book book,
                                  @MappingTarget BooksResponseDTO dto) {
        if (book.getAuthor() != null) {
            dto.setAuthorFirstName(book.getAuthor().getFirstName());
            dto.setAuthorLastName(book.getAuthor().getLastName());
        }
    }
}
