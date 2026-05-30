package com.acc.libraryManagement.services;

import com.acc.common_lib.exceptions.ResourceNotFoundException;
import com.acc.libraryManagement.projections.BorrowRecordProjection;
import com.acc.libraryManagement.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {
    private final BorrowRepository borrowRepository;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public List<BorrowRecordProjection> getAllBorrowRecords(){
        return borrowRepository.findAllBorrowRecords();
    }

    public List<BorrowRecordProjection> getAllOverDueBorrowRecords(){
        return borrowRepository.findAllOverDueBorrowRecords();
    }

    public BorrowRecordProjection getBorrowById(Long borrowId){
        BorrowRecordProjection record =  borrowRepository.findBorrowById(borrowId);
        if(record == null){
            throw new ResourceNotFoundException("Borrow record not found with id: " + borrowId);
        }
        return record;
    }
}
