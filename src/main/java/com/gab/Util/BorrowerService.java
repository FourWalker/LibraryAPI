package com.gab.Util;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gab.Models.BookRepo;
import com.gab.Models.Borrower;
import com.gab.Models.BorrowerRepo;

@Service
public class BorrowerService {

	

	BorrowerRepo borrowerRepo; 
	
	public BorrowerService(BorrowerRepo borrowerRepo, BookRepo bookRepo) {
		this.borrowerRepo = borrowerRepo;
	}

	
	
	public ResponseEntity<?> registerNewBorrower(Borrower borrower) {
		if(borrowerRepo.findByNameAndEmail(borrower.getName(), borrower.getEmail()).orElse(null) != null) {
			return new ResponseEntity<>("User already exists",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(save(borrower),HttpStatus.CREATED);
		
	}
	
	public Borrower save(Borrower borrower) {
		return borrowerRepo.save(borrower);
	}
	
}
