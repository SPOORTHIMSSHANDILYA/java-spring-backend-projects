package com.acc.libraryManagement.projections;

import java.time.LocalDate;

public interface BorrowRecordProjection {
    Long getId();
    Long getMemberId();
    String getMemberName();
    Long getBookId();
    String getBookTitle();
    String getBookIsbn();
    LocalDate getBorrowDate();
    LocalDate  getDueDate();
    LocalDate  getReturnDate();
    String getStatus();
}