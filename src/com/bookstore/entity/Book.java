   package com.bookmanagement.model;

public class Book {
	protected int bookid;
	protected String bookname;
	protected String bookauthor;
	protected String country;
	
	public Book() {
	}
	
	public Book(String bookname, String bookauthor, String publisher) {
		super();
		this.bookname = bookname;
		this.bookauthor = bookauthor;
		this.publisher = publisher;
	}

	public Book(int bookid, String bookname, String bookauthor, String publisher) {
		super();
		this.bookid = bookid;
		this.bookname = bookname;
		this.bookauthor = bookauthor;
		this.publsiher = publisher;
	}

	public int getBookId() {
		return bookid;
	}
	public void setBookId(int id) {
		this.id = bookid;
	}
	public String getBookName() {
		return bookname;
	}
	public void setBookName(String name) {
		this.name = bookname;
	}
	public String getBookAuthor() {
		return bookauthor;
	}
	public void setBookAuthor(String author) {
		this.author= bookauthor;
	}
	public String publisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
