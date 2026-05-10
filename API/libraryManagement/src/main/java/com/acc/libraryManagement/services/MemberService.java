package com.acc.libraryManagement.services;

import com.acc.common_lib.exceptions.ResourceNotFoundException;
import com.acc.common_lib.models.PagedResponseDTO;
import com.acc.libraryManagement.entities.Member;
import com.acc.libraryManagement.mapper.MemberMapper;
import com.acc.libraryManagement.models.MemberRequestDTO;
import com.acc.libraryManagement.models.MemberResponseDTO;
import com.acc.libraryManagement.projections.BorrowRecordProjection;
import com.acc.libraryManagement.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper){
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public PagedResponseDTO<MemberResponseDTO> getAllMembersWithPagination(int page, int size, String sortBy, String direction){
        //build sort
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        //build pageable
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<MemberResponseDTO> pagedResponse = memberRepository.findAllWithActiveBorrows(pageable);
        List<MemberResponseDTO> pagedResponseList = pagedResponse.getContent().stream()
                .toList();
        return new PagedResponseDTO<>(pagedResponseList,
                pagedResponse.getNumber(),
                pagedResponse.getSize(),
                pagedResponse.getTotalElements(),
                pagedResponse.getTotalPages(),
                pagedResponse.isLast(),
                pagedResponse.isFirst());
    }

    public MemberResponseDTO getMembersById(int id){
        MemberResponseDTO member = memberRepository.findByMemberId((long) id);
        if (member == null) {
            throw new ResourceNotFoundException("Member with ID " + id + " not found in database");
        }
        return member;
    }

    public List<BorrowRecordProjection> getMemberBorrowHistory(int memberId){
        List<BorrowRecordProjection> memberBorrowHistory =  memberRepository.findBorrowRecordsById((long)memberId);
        if (memberBorrowHistory == null) {
            throw new ResourceNotFoundException("Member with ID " + memberId + " not found in database");
        }
        return memberBorrowHistory;
    }

    public MemberResponseDTO saveMember(MemberRequestDTO memberRequest){
        Member member = memberMapper.toEntity(memberRequest);
        memberRepository.save(member);
        return memberMapper.toDto(member);
    }

    public MemberResponseDTO editMember(MemberRequestDTO memberRequest,int id) {
        Member member = memberRepository.findById((long) id).orElseThrow(() -> new ResourceNotFoundException("Member with ID " + id + " not found in database"));;
        member.setFirstName(memberRequest.getFirstName());
        member.setLastName(memberRequest.getLastName());
        member.setEmail(memberRequest.getEmail());
        member.setPhone(memberRequest.getPhone());
        memberRepository.save(member);
        return memberMapper.toDto(member);
    }

    public String deactivateMember(int id){
        Member member = memberRepository.findById((long) id).orElseThrow(() -> new ResourceNotFoundException("Member with ID " + id + " not found in database"));
        if(!member.getIsActive()){
            return "Member with ID " + id + " is already deactivated";
        }
        else{
            member.setIsActive(false);
            memberRepository.save(member);
            return "Member with ID " + id + " has been deactivated successfully";
        }
    }
}
