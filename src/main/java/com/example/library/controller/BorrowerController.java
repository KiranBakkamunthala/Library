package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Borrower;
import com.example.library.service.BorrowerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/borrowers")
@Validated
public class BorrowerController {

	@Autowired
	private BorrowerService borrowerService;

	@PostMapping
	public ResponseEntity<Borrower> registerBorrower(@Valid @RequestBody Borrower borrower) {
		return ResponseEntity.ok(borrowerService.saveBorrower(borrower));
	}

	@GetMapping
	public ResponseEntity<List<Borrower>> getAllBorrowers() {
		return ResponseEntity.ok(borrowerService.getAllBorrowers());
	}
}
