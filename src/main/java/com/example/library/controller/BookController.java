package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Book;
import com.example.library.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	// Register a new book to the library
	@PostMapping
	public ResponseEntity<Book> registerBook(@RequestBody Book book) {
		Book savedBook = bookService.saveBook(book);
		return ResponseEntity.ok(savedBook);
	}

	// fetch a list of all books in the library
	@GetMapping
	public ResponseEntity<List<Book>> getAvailableBooks() {
	    return ResponseEntity.ok(bookService.getAvailableBooks());
	}

}
