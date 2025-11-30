
package com.gab.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate; 
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "borrowedbooks") 
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long borrowerid;
    
    
    @NotNull(message="Book Id is required")
    @JsonProperty("bookid")
    private long bookid;
    
    @Column(name = "borrowDate", nullable = false)
    private LocalDate borrowDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "returnedDate")
    private LocalDate returnedDate;
    
    @NotEmpty(message = "name must not be empty")
	private String name;
    
    @Email(message = "Email format is not correct")
    @NotEmpty(message = "Email must not be empty")
    private String email;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getbookid() {
    	return bookid;
    }

    public void setbookid(long bookid) {
    	this.bookid = bookid;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }
}