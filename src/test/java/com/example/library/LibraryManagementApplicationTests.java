package com.example.library;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRecordRepository;
import com.example.library.repository.BorrowerRepository;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryManagementApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository; // ✅ ADD THIS

   
    @BeforeEach
    public void setUp() {
        // ✅ Delete in correct order: child -> parent
        borrowRecordRepository.deleteAll();
        bookRepository.deleteAll();
        borrowerRepository.deleteAll();
    }

    @Test
    public void testCreateBook() throws Exception {
        String bookJson = "{\"isbn\":\"123456789\",\"title\":\"Sample Book\",\"author\":\"Author Name\"}";

        mockMvc.perform(post("/api/books") // ✅ Fixed extra `/api/`
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testBorrowBook() throws Exception {

        Book book = new Book("123456789", "Sample Book", "Author Name");
        book = bookRepository.save(book);

        Borrower borrower = new Borrower("John Doe", "john.doe@example.com");
        borrower = borrowerRepository.save(borrower);

        mockMvc.perform(post("/api/borrow-records/borrow") // ✅ Fixed extra `/api/`
                .param("borrowerId", String.valueOf(borrower.getId()))
                .param("isbn", "123456789"))
                .andExpect(status().isOk());
    }

 }