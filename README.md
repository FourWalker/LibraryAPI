LibraryAPI – Documentation

This API simulates a basic library borrow and return book system, it lets the API user borrow a book on behalf of the “Borrower”, the Book can be borrowed using its Book ID (bookid), a borrow transaction (borrowBook) needs the email and name of the registered Borrower together with the Book ID.
A table that contains borrow transaction (borrowedbook table) uses borrowerid and bookid to relate Book and Borrower table.  If the Email and Name does not match an existing borrower record provided in the “borrowBook” transaction, it will not proceed with the transaction as the Name and Email will be used as an argument to find a borrower’s id (borrowerid). 
ISBN number uniquely identifies a book in the following way: 2 books with the same title and same author but different ISBN numbers are considered, 2 books with the same ISBN numbers must have the same title and same author
Multiple copies of books with same ISBN number are allowed in the system.

Instructions on running the Application
The datasource of the application has predefined datasource credentials and url. You can either run the application via persistence or create a docker container using this command and run it with MySql with the predefined credential and url.


docker run --detach --env MYSQL_ROOT_PASSWORD=admin --env MYSQL_USER=librarian --env MYSQL_PASSWORD=librarian --env MYSQL_DATABASE=library --name mysqldocker --publish 3307:3306 mysql:8-oracle

port 3307 is used in case an already existing mysqlserver (that by default uses 3306) is using the 3306 port.
If you want to run the application using a persistence data source. Follow the steps indicated in application.properties
application.properties file – follow the comment instruction.
 


**Endpoints returned Content**
By default, content returned by the end points are in JSON format, you can request for an xml format by adding “Accept”:”application/xml” in headers.
 

**/Library/registerBook **
This handles creating / registering a new book for the library record. It accepts book title, author, and ISBN from Request Body


_Request Body_
{

  "title": "string",
 
  "author": "string",
 
  "isbn": 1

}



_Validations_
•	2 books with the same title and same author but different ISBN numbers are considered as different books
•	2 books with the same ISBN numbers must have the same title and same author
•	Multiple copies of books with same ISBN number are allowed in the system
•	Title, Author, and ISBN is required. It will return error message indicating missing or required fields if any is missing.
 
•	It will return an error message if the criteria for ISBN is not met.
 





**/Library/registerBorrower**
This handles borrower registration for the library record, it accepts email and name from Request Body.


_Request Body_
{
  "name": "string",
  "email": "user@example.com"
}

 
_Validations_
•	Name and Email is required, it will return an error message specifying which of the required fields are missing or if the email has incorrect format.
•	If a new record matches an existing borrower with same name and email, it will return an error message indicating that a record of the borrower already exists.
 





**/Library/borrowBook**
This handles book borrowing system, it accepts Borrower Email and Name, Book ID and BorrowDate (optional, will return current time if not specified) from Request Body.

_Request Body_

{
 
  "borrowDate": "2025-12-03",
 
  "name": "string",

  "email": "user@example.com",

  "bookid": 0

}
 
_Validations_
•	Requires name, email, and bookid. It will return an error message specifying the missing fields if there are missing.
•	If Book is already borrowed (is in borrowed table and returned date is null), it will return error message indicating that the book is already borrowed.
•	If Book does not exist in the record (bookid did not match any id from book table), it will return error message indicating that the book does not exist.
•	If the ‘Name and Email’ did not match any ‘Name and Email’ in the borrowers table, it will return an error message indicating that the borrower record does not exist.







/Library/returnBook
This handles book returning system, it accepts the Book ID and Returned Date (optional, will return currect date if not specified) from Request Body.

_Request Body_

{
 
  "returnedDate": "2025-12-03",

  "bookId": 1

}
 
_Validations_
•	bookId is required and will return an error message if its missing.
•	If the Book is not borrowed / returned / does not exist, it will return an error message saying that the book is either not borrowed or does not exist.






**/Library/getAllBooks**
This returns all books saved in table. No Request Body or parameter required.



 

Database – Specification Assumptions - Justify database type used and assumed specification that is not part of the requirements

I used SQL Database because I decided to add another table that will hold all the borrowed books, if the requirement specifically requires to only use two data models (Book,Borrower), I would opt to using NoSQL Database, I would add all the “Book” data to “Borrower”
 
when they borrow a book, this way you can easily list down all the borrowed books from the data of the borrower, while this approach solves the two data model problem, it creates another specification since the model is being changed to including Book Model into Borrower’s.

In case of future adjustments in validations before creating or manipulating data, all proper validations are and can be placed in their proper BeanService.class, Repos are only accessible from Services and is not exposed to Controllers.

It is not indicated in Borrowing Process whether BorrowerID should supplement the Borrower Identity similar to Book Id for the Book, what I implemented is supplying the borrower’s identity via Name and Email (this will check if this combination exists in the Borrower table), it’s a very simple and basic form of Authorization like Name and Pass instead of having to just input their Borrower ID, Borrowers are more likely to know their Name/Email instead of the system’s assigned ID to their record, This way, it is also possible to create a new record of Borrower if the Name/Email didn’t match existing record enabling fewer steps in compare to finding the borrower id or creating their record and performing borrow book process.

It is also not indicated in the requirements / specifications how to process or record data of returned or borrowed books, my implementation involves adding BorrowedBook Table, this contains the book id, borrower id, borrow date and return date. A borrowed book is considered returned once the field “ReturnedDate” is not null or not empty, this way the “borrowBook” process can identify if the book is already returned if it found the book id in the BorrowedBook Table, this also help identify whether the book can be borrowed or not.








	
