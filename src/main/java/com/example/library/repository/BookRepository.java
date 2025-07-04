package com.example.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	// To find an available book by ISBN
	Optional<Book> findFirstByIsbnAndIsBorrowedFalse(String isbn);

	// To list all available books
	List<Book> findByIsBorrowedFalse();
	
	List<Book> findByIsbn(String isbn);
}


