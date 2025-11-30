package com.gab.Models;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BorrowedBookRepo extends JpaRepository<BorrowedBook, Integer> {

	Optional<BorrowedBook> findByBookidAndReturnedDateIsNull(long bookid);
	
	Optional<BorrowedBook> findByBookidAndReturnedDate(long bookid, LocalDate returnedDate);
	
	
}
