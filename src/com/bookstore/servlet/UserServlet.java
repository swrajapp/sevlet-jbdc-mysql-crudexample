package com.bookstore.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;

import com.bookmanagement.model.Book;



@WebServlet("/bookstore")
public class bookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;
	
	public void init() {
		bookDAO = new BookDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertBook(request, response);
				break;
			case "/delete":
				deleteBook(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateBook(request, response);
				break;
			default:
				listBook(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listBook()
			throws SQLException, IOException, ServletException {
		List<Book> listBook = bookDAO.selectAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book existingBook = bookDAO.selectBook(bookid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
		request.setAttribute("book", existingbook);
		dispatcher.forward(request, response);

	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("bookname");
		String author = request.getParameter("bookauthor");
		String publication = request.getParameter("publication");
		Book newBook = new Book(bookname, bookauthor, publication);
		bookDAO.insertBook(newBook);
		response.sendRedirect("list");
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("bookid"));
		String name = request.getParameter("bookname");
		String author = request.getParameter("bookauthor");
		String publication = request.getParameter("publication);

		Book book = new book(bookid, bookname, bookauthor, publication);
		bookDAO.updateBook(book);
		response.sendRedirect("list");
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int bookid = Integer.parseInt(request.getParameter("bookid"));
		bookDAO.deleteBook(bookid);
		response.sendRedirect("list");
	}
}
