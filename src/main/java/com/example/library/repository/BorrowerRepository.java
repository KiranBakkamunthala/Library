package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.model.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
}
