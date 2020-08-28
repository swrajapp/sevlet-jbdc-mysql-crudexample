<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Book Management Application</title>
</head>
<body>
	<center>
		<h1>Book Management</h1>
        <h2>
        	<a href="new">Add New User</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Users</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Books</h2></caption>
            <tr>
                <th>BookID</th>
                <th>BookName</th>
                <th>BookAuthor</th>
                <th>publication</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="book" items="${listBook}">
                <tr>
                    <td><c:out value="${book.bookid}" /></td>
                    <td><c:out value="${book.bookname}" /></td>
                    <td><c:out value="${book.bookauthor}" /></td>
                    <td><c:out value="${book.publication}" /></td>
                    <td>
                    	<a href="edit?id=<c:out value='${book.bookid}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="delete?id=<c:out value='${book.bookid}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
