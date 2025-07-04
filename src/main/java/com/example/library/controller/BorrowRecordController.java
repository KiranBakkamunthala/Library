package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.service.BorrowRecordService;

@RestController
@RequestMapping("/api/borrow-records")
public class BorrowRecordController {

	@Autowired
	private BorrowRecordService borrowRecordService;

	@PostMapping("/borrow")
	public ResponseEntity<String> borrowBook(@RequestParam Long borrowerId, @RequestParam String isbn) {
		try {
			borrowRecordService.borrowBook(borrowerId, isbn);
			return ResponseEntity.ok("Book borrowed successfully.");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/{borrowRecordId}/return")
	public ResponseEntity<String> returnBook(@PathVariable Long borrowRecordId) {
		try {
			borrowRecordService.returnBook(borrowRecordId);
			return ResponseEntity.ok("Book returned successfully.");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
