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
        	<a href="new">Add New Book</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Books</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${book != null}">
			<form action="update" method="post">
        </c:if>
        <c:if test="${book == null}">
			<form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${book != null}">
            			Edit Book
            		</c:if>
            		<c:if test="${book == null}">
            			Add New Book
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${book!= null}">
        			<input type="hidden" name="id" value="<c:out value='${book.bookid}' />" />
        		</c:if>            
            <tr>
                <th>Book Name: </th>
                <td>
                	<input type="text" name="bookname" size="45"
                			value="<c:out value='${book.name}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Book Author: </th>
                <td>
                	<input type="text" name="bookauthor" size="45"
                			value="<c:out value='${book.bookauthor}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Publication: </th>
                <td>
                	<input type="text" name="publication" size="15"
                			value="<c:out value='${book.publication}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
