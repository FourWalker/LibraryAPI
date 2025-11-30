package com.gab.Models;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
	List<Book> findByisbn(long isbn);
	
}
