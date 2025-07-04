package com.example.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.model.Book;
import com.example.library.model.BorrowRecord;
import com.example.library.model.Borrower;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRecordRepository;
import com.example.library.repository.BorrowerRepository;

@Service
public class BorrowRecordService {

	@Autowired
	private BorrowRecordRepository borrowRecordRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BorrowerRepository borrowerRepository;

	public BorrowRecord borrowBook(Long borrowerId, String isbn) {
	    Borrower borrower = borrowerRepository.findById(borrowerId)
	            .orElseThrow(() -> new RuntimeException("Borrower not found"));

	    Book book = bookRepository.findFirstByIsbnAndIsBorrowedFalse(isbn)
	            .orElseThrow(() -> new RuntimeException("No available book with this ISBN"));

	    // Mark book as borrowed
	    book.setBorrowed(true);
	    bookRepository.save(book);

	    // Create borrow record
	    BorrowRecord record = new BorrowRecord(borrower, book, false);
	    return borrowRecordRepository.save(record);
	}

	public void returnBook(Long borrowRecordId) {
	    BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId)
	            .orElseThrow(() -> new RuntimeException("Borrow record not found"));

	    if (borrowRecord.isReturned()) {
	        throw new RuntimeException("This book has already been returned.");
	    }

	    borrowRecord.setReturned(true);
	    borrowRecordRepository.save(borrowRecord);

	    // Also mark book as available again
	    Book book = borrowRecord.getBook();
	    book.setBorrowed(false);
	    bookRepository.save(book);
	}
	
	
}
