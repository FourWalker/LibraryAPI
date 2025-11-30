/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.gab.Models;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


/**
 *
 * @author pgdeg
 */


@Component
@Entity(name="book")
public class Book {

	@Id
	@GeneratedValue
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    
    @NotEmpty(message = "Title must not be empty")
    private String title;
	
	@NotEmpty(message = "Author must not be empty")
    private String author;
	
	@NotNull(message = "ISBN must not be empty")
	@Min(value = 1,message = "ISBN must not be empty")
    private Integer isbn;

	
    public Book(int id, String title, String author, Integer ISBN) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = ISBN;
    }
    public Book(String title, String author, Integer ISBN) {
        this.title = title;
        this.author = author;
        this.isbn = ISBN;
    }
    public Book() {

	}
    
	public String getAuthor() {
		return author;
	}
	public Integer getISBN() {
		return isbn;
	}
	public long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	
	

}
