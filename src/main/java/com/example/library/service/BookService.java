package com.example.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.exception.InvalidIsbnException;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Book saveBook(Book book) {
	    // Check if ISBN already exists
	    List<Book> existingBooks = bookRepository.findByIsbn(book.getIsbn());

	    for (Book existing : existingBooks) {
	        if (!existing.getTitle().equalsIgnoreCase(book.getTitle()) ||
	            !existing.getAuthor().equalsIgnoreCase(book.getAuthor())) {
	            throw new InvalidIsbnException("ISBN already exists with a different title/author");
	        }
	    }

	    return bookRepository.save(book);
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public List<Book> getAvailableBooks() {
	    return bookRepository.findByIsBorrowedFalse();
	}
}
