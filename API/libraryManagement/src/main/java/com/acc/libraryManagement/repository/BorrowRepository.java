package com.acc.libraryManagement.repository;

import com.acc.libraryManagement.entities.BorrowRecord;
import com.acc.libraryManagement.projections.BorrowRecordProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowRecord, Long> {

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
            ORDER BY br.borrow_date DESC;
            """,nativeQuery = true)
    List<BorrowRecordProjection> findAllBorrowRecords();


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
            where br.status = 'OVERDUE'\s
            or( br.status = 'BORROWED' and br.due_date < curdate())
            ORDER BY br.borrow_date DESC;
            """,nativeQuery = true)
    List<BorrowRecordProjection> findAllOverDueBorrowRecords();


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
            where br.id = :borrowId
            ORDER BY br.borrow_date DESC;
            """,nativeQuery = true)
    BorrowRecordProjection findBorrowById(@Param("borrowId") Long borrowId);
}
