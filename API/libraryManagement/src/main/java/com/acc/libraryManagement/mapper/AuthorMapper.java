package com.acc.libraryManagement.mapper;

import com.acc.libraryManagement.entities.Author;
import com.acc.libraryManagement.models.AuthorRequestDTO;
import com.acc.libraryManagement.models.AuthorResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorResponseDTO toDTO(Author author);

    Author toEntity(AuthorRequestDTO dto);
}