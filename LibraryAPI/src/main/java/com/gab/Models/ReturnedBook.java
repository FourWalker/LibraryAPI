package com.gab.Models;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReturnedBook {

	
	public LocalDate returnedDate;
	
	@NotNull(message="Book Id is required")
	@Min(value=1,message="Book Id is required")
	public long bookId;
	
}
