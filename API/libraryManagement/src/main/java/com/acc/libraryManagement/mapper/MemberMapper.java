package com.acc.libraryManagement.mapper;

import com.acc.libraryManagement.entities.Member;
import com.acc.libraryManagement.models.MemberRequestDTO;
import com.acc.libraryManagement.models.MemberResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberResponseDTO toDto(Member member);

    Member toEntity(MemberRequestDTO memberRequestDTO);
}