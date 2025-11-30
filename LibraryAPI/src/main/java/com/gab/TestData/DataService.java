/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.gab.TestData;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.gab.Models.Book;
import com.gab.Models.BorrowedBook;
import com.gab.Models.Borrower;

/**
 *
 * @author pgdeg
 */

@Component
public class DataService {
    public static ArrayList<Book> booksDB = new ArrayList<Book>();
    public static ArrayList<BorrowedBook> borrowedbooksDB = new ArrayList<BorrowedBook>();
    public static ArrayList<Borrower> borrowersDB = new ArrayList<Borrower>();

    
    public static int bookIndex = 0;
    public static int borrowedBookIndex = 0;
    public static int borrowerIndex = 0;
    public static void initTestData(){
        booksDB.add(new Book(++bookIndex, "Book 1", "Author 1", 100));
        booksDB.add(new Book(++bookIndex, "Book 1", "Author 1", 100));
        booksDB.add(new Book(++bookIndex, "Book 2", "Author 1", 200));
        booksDB.add(new Book(++bookIndex, "Book 3", "Author 1", 300));
        booksDB.add(new Book(++bookIndex, "Book 1", "Author 1", 100));
        borrowersDB.add(new Borrower(1,"Pedro","pdeguzma@gmail.com"));
        
    }
    
    public static Book saveBook(Book b) {
    	booksDB.add(b);
    	return b;
    }
    
    public static Borrower saveBorrower(Borrower b) {
    	borrowersDB.add(b);
    	return b;
    }
    
    public static BorrowedBook saveBorrowedBook(BorrowedBook b) {
    	borrowedbooksDB.add(b);
    	return b;
    }





    

}
