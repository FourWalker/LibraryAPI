/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.gab.LibraryAPI;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gab.Models.Book;
import com.gab.Models.BookRepo;
import com.gab.Models.BorrowedBook;
import com.gab.Models.BorrowedBookRepo;
import com.gab.Models.Borrower;
import com.gab.Models.BorrowerRepo;
import com.gab.Models.ReturnedBook;
import com.gab.TestData.DataService;
import com.gab.Util.BookService;
import com.gab.Util.BorrowerService;

import jakarta.validation.Valid;

/**
 *
 * @author pgdeg
 */

@RestController
public class LibraryAPIResourceJPA{


	
	BookService bookService;
	BorrowerService borrowerService;
    
	public LibraryAPIResourceJPA(BookRepo bookRepo, BorrowerRepo borrowerRepo, BorrowedBookRepo borrowedBookRepo, BookService bookService, BorrowerService borrowerService) {
		this.bookService = bookService;
		this.borrowerService = borrowerService;
	}
	
    
    @GetMapping(path = "/Library/getAllBooks")
    public List<Book>getBooks(){
        return bookService.getAllBooks();
    }
    
    @PostMapping(path = "/Library/registerBook")
    public ResponseEntity<?> registerBook(@Valid @RequestBody Book book){ 
    	return bookService.registerNewBook(book);
	}
    
    @PostMapping(path = "/Library/registerBorrower")
    public ResponseEntity<?> registerBorrower(@Valid @RequestBody Borrower borrower){ 
		return borrowerService.registerNewBorrower(borrower);
	}
    
    
    @PostMapping(path = "/Library/borrowBook")
    public ResponseEntity<?> borrowBook(@Valid @RequestBody BorrowedBook bbook){ 
		return bookService.borrowBook(bbook);
		
	}
    
    @PostMapping(path = "/Library/returnBook")
    public ResponseEntity<?> borrowBook(@Valid @RequestBody ReturnedBook bbook){ 
		return bookService.returnBook(bbook);
		
	}
    
	
    
}

//Override default validation message to provide specific field with error
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

