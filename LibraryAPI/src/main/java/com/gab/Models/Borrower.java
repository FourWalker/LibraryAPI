/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.gab.Models;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gab.TestData.DataService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

/**
 *
 * @author pgdeg
 */

@Component
@Entity(name="borrower")
public class Borrower {
	
	@Id
	@GeneratedValue
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer borrowerid;
    
    @NotEmpty(message = "name must not be empty")
    private String name;
    
    @Email(message = "Email format is not correct")
    @NotEmpty(message = "Email must not be empty")
    private String email;


    public Borrower(int id, String name, String email) {
        this.borrowerid = id;
        this.name = name;
        this.email = email;
    }
    
    public Borrower(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public Borrower() {
    	
    }
    
    //Returns the borrower id if only the name and email address is suplemented
    //This is needed for BorrowedBook class to form a "Borrowing" relationship with Borrower and the Book.
    @JsonIgnore
    public Integer getIdByNameandEmail() {
		
    	Borrower b = DataService.borrowersDB.stream().filter(borrower -> borrower.getName().equals(this.name) && borrower.getEmail().equals(this.email)).findFirst().orElse(null);
    	borrowerid = b != null ? b.borrowerid() : null;
    	return borrowerid;
	}


	public Integer borrowerid() {
		return borrowerid;
	}


	public String getName() {
		return name;
	}


	public String getEmail() {
		return email;
	}
    
    
    


    
    
    

    

}
