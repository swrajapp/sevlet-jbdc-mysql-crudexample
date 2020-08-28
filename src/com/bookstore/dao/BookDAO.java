package com.bookstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.User;


public class BookDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_USERS_SQL = "INSERT INTO books" + "  (bookid,bookname,bookauthor,publication) VALUES "
			+ " (?, ?, ?,?);";

	private static final String SELECT_Book_BY_ID = "select bookid,bookname,bookauthor,publication from books where bookid =?";
	private static final String SELECT_ALL_Books = "select * from books";
	private static final String DELETE_Books_SQL = "delete from books where bookid = ?;";
	private static final String UPDATE_Books_SQL = "update books set bookname = ?,bookauthor= ?,publication=? where bookid = ?;";

	public BookDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertBook(Book book) throws SQLException {
		System.out.println(INSERT_Books_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Books_SQL)) {
			preparedStatement.setString(1, book.getBookName());
			preparedStatement.setString(2, book.getBookAuthor());
			preparedStatement.setString(3, book.getPublication());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Book selectBook(int bookid) {
		Book book = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_BOOKID);) {
			preparedStatement.setInt(1, bookid);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String bookname = rs.getString("bookname");
				String bookauthor= rs.getString("bookauthor");
				String publication = rs.getString("publication");
				book = new Book(bookid, bookname, bookauthor,publication);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return book;
	}

	public List<Book> selectAllBooks() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Book> books = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Books);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int bookid = rs.getInt("bookid");
				String bookname = rs.getString("bookname");
				String bookauthor = rs.getString("bookauthor");
				String publication = rs.getString("publication");
				books.add(new Book(bookid, bookname, bookauthor, publication));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return books;
	}

	public boolean deleteBook(int bookid) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_BOOKS_SQL);) {
			statement.setInt(1, bookid);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateBook(Book book) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_BOOKS_SQL);) {
			statement.setString(1, book.getBookName());
			statement.setString(2, book.getBookAuthor());
			statement.setString(3, book.getPublication());
			statement.setInt(4, book.getBookId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
