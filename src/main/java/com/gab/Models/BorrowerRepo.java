package com.gab.Models;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepo extends JpaRepository<Borrower, Integer> {

	Optional<Borrower> findByNameAndEmail(String name, String email);
	
	
}
