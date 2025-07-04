Technicial stack:
================

i am developing a Spring Boot application using version 3.3.2 with Java 17 and an H2 in-memory database. 
The application includes RESTful web services for APIs, which are tested using Postman. You use Spring Boot 
Actuator for health, info, and metrics checks, and employ the Validator to validate properties. 
For testing, you use JUnit to run test cases and measure code coverage, while Mockito is utilized to mock the repository.
jacoco plug in for code coverage checking.

Description About Overall Application:
=====================================

I created three controller classes to handle six APIs and used Postman to store data in the H2 database.

@PostMapping for Creating Books: This endpoint allows users to register a new book to the library. It accepts a Book object in the request body and returns the created book.

@GetMapping for Listing All Books: This endpoint retrieves a list of all books in the library and returns it as a JSON response.

@GetMapping("/borrow/{isbn}") for Borrowing a Book: This endpoint allows a borrower to borrow a book based on the ISBN. It requires the ISBN 
of the book and a borrower ID as request parameters. This endpoint will typically call a method in the BookService to handle the borrowing logic, but this functionality might be better suited in a separate controller (e.g., BorrowRecordController).

@PostMapping("/return/{borrowRecordId}") for Returning a Book: This endpoint handles returning a borrowed book using the borrow record ID. 
This should ideally be in a separate controller related to borrow records.

BorrowRecordService: Handles the business logic for borrowing and returning books.
BookService: Manages CRUD operations for books.
BorrowRecordController: Provides endpoints for borrowing and returning books.


Junit test Cases:
=================
I have written JUnit regression test cases; two passed while one failed due to issues with the in-memory database. 
I apologize for any inconvenience caused. The controller, service, and repository test cases were not written due to
 insufficient time. Additionally, I had a production promotion yesterday.

Code coverage :
============== 
I added the JaCoCo plugin to the pom.xml and checked the code coverage report.
