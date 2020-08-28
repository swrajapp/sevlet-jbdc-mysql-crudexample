CREATE DATABASE 'demo';
USE demo;

create table books (
	bookid  int(10) NOT NULL AUTO_INCREMENT,
	bookname varchar(120) NOT NULL,
	bookauthor varchar(220) NOT NULL,
	publication varchar(120),
	PRIMARY KEY (bookid)
);

