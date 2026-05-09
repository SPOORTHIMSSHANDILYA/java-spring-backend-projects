package com.acc.libraryManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrow_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "borrow_date", nullable = false)
    private LocalDateTime borrowDate;

    @Column(name = "due_date",nullable = false)
    private LocalDateTime dueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",length = 20, nullable = false)
    private Status status = Status.BORROWED;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Many records → One book
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    // Many records →  One member
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public enum Status {
        BORROWED,
        RETURNED,
        OVERDUE
    }

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
        borrowDate = LocalDateTime.now();
        dueDate = borrowDate.plusDays(14);
    }
}
