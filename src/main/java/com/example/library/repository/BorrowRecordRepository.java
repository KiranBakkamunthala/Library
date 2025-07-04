package com.example.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.model.Book;
import com.example.library.model.BorrowRecord;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
	Optional<BorrowRecord> findByBookAndReturned(Book book, boolean returned);
}