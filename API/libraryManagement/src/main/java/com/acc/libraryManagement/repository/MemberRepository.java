package com.acc.libraryManagement.repository;

import com.acc.libraryManagement.entities.Member;
import com.acc.libraryManagement.models.MemberResponseDTO;
import com.acc.libraryManagement.projections.BorrowRecordProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = """
            select m.id as id,m.first_name as firstName, m.last_name as lastName, m.email as email, m.phone as phone,
            m.membership_date as membershipDate, m.is_active as active,count(br.id) as activeBorrows
            from members m
            left join borrow_records br on m.id = br.member_id and br.status = 'BORROWED'
            group by m.id
            """, nativeQuery = true)
    Page<MemberResponseDTO> findAllWithActiveBorrows(Pageable pageable);


    @Query(value = """
            select m.id as id,m.first_name as firstName, m.last_name as lastName, m.email as email, m.phone as phone,
            m.membership_date as membershipDate, m.is_active as active,count(br.id) as activeBorrows
            from members m
            left join borrow_records br on m.id = br.member_id and br.status = 'BORROWED'
            where m.id = :id
            group by m.id
            """, nativeQuery = true)
    MemberResponseDTO findByMemberId(@Param("id") Long id);

    @Query(value = """
            select br.id as id,m.id as memberId,concat(m.first_name," ",m.last_name) as memberName,b.id as bookId, b.title as bookTitle, b.isbn as bookIsbn,br.borrow_date as borrowDate,
            br.due_date as dueDate,br.return_date as returnDate,
            case
            when br.status = 'BORROWED' and br.due_date < curdate() then 'OVERDUE'
            else br.status
            End as status
            from members m
            join borrow_records br on br.member_id = m.id
            join books b on br.book_id = b.id
            where m.id = :memberId
            ORDER BY br.borrow_date DESC;
            """,nativeQuery = true)
    List<BorrowRecordProjection> findBorrowRecordsById(@Param("memberId")Long memberId);

    boolean existsByEmail(String email);
}
