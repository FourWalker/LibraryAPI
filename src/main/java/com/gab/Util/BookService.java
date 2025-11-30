/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.gab.Util;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gab.Models.Book;
import com.gab.Models.BookRepo;
import com.gab.Models.BorrowedBook;
import com.gab.Models.BorrowedBookRepo;
import com.gab.Models.BorrowerRepo;
import com.gab.Models.ReturnedBook;
import com.gab.TestData.DataService;

/**
 *
 * @author pgdeg
 */

@Service
public class BookService {

	
	BookRepo bookRepo; 
	BorrowedBookRepo borrowedBookRepo;
	BorrowerRepo borrowerRepo;
	
	public BookService(BookRepo bookRepo, BorrowedBookRepo borrowedBookRepo, BorrowerRepo borrowerRepo) {
		this.bookRepo = bookRepo;
		this.borrowedBookRepo = borrowedBookRepo;
		this.borrowerRepo = borrowerRepo;
	}

    public static boolean isBorrowed(long bookid){
    	
    	return DataService.borrowedbooksDB.stream().filter(borrowedbook -> borrowedbook.getbookid() == bookid && borrowedbook.getReturnedDate() == null).findAny().orElse(null) == null;
        
    }
    
    
    public List<Book> getAllBooks(){ 
    	return bookRepo.findAll();   
	}
    
    
    public ResponseEntity<?> registerNewBook(Book book) {
    	//Future validations when creating a new book here
    	if(!validateISBN(book)){
			return new ResponseEntity<>("ISBN entry already exists, make sure the new book matches the existing book Title and Author with the same ISBN",HttpStatus.BAD_REQUEST);
		}
		save(book);
    	return new ResponseEntity<>(book,HttpStatus.CREATED);
	}
    
    public Book save(Book book) {
		return bookRepo.save(book);
	}
    
    public Book findById(int id) {
    	return bookRepo.findById(id).orElse(null);
    }
    
    public BorrowedBook findBorrowedBook(long id) {
		return borrowedBookRepo.findByBookidAndReturnedDateIsNull(id).orElse(null);
	}
    
    public BorrowedBook findUnreturnedBook(long id) {
		return borrowedBookRepo.findByBookidAndReturnedDate(id, java.time.LocalDate.now()).orElse(null);
	}
    
    public ResponseEntity<?> returnBook(ReturnedBook returnedBook){
    	
    	BorrowedBook bbook = findBorrowedBook(returnedBook.bookId);
    	if(bbook == null) {
    		return new ResponseEntity<>("Book either does not exist or is not borrowed",HttpStatus.BAD_REQUEST);
    	}
    	
    	if(returnedBook.returnedDate == null) {
	    	returnedBook.returnedDate = java.time.LocalDate.now();
    	}
    	
    	bbook.setReturnedDate(returnedBook.returnedDate);
    	
    	bbook.setReturnedDate(java.time.LocalDate.now());
    	borrowedBookRepo.save(bbook);
    	
    	
    	return new ResponseEntity<>(bbook,HttpStatus.OK);
    }
    
	public ResponseEntity<?> borrowBook(BorrowedBook bbook){
		
		if(findById((int)bbook.getbookid()) == null) {
    		return new ResponseEntity<>("Book does not exist",HttpStatus.BAD_REQUEST);
    	}
    	if(findBorrowedBook((int)bbook.getbookid()) != null){
    		return new ResponseEntity<>("Book is already borrowed",HttpStatus.BAD_REQUEST);
    	}
    	
    	if(borrowerRepo.findByNameAndEmail(bbook.getName(), bbook.getEmail()).orElse(null) == null) {
    		return new ResponseEntity<>("User does not exist",HttpStatus.BAD_REQUEST);
			
    	}
    	
    	if(bbook.getBorrowDate() == null) {
			bbook.setBorrowDate(java.time.LocalDate.now());
		}
		
    	
    	borrowedBookRepo.save(bbook);
    	return new ResponseEntity<>(bbook,HttpStatus.OK);
	}
    
    
    public boolean validateISBN(Book book) {
    	//Check if BOOK with the same ISBN already exists and if the new Book has the same Title and Author of the existing
		String title = book.getTitle();
		String author = book.getAuthor();
		Integer isbn = book.getISBN();
		
		Book b = bookRepo.findByisbn(isbn).stream().findFirst().orElse(null);
		if(b != null) {
			if (!b.getAuthor().equals(author) || !b.getTitle().equals(title) ){
	    		return false;
	    	}
		}
	
    	return true;
    }
    
   
    

}
