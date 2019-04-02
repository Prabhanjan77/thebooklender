package com.project.thebooklender.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.thebooklender.bean.*;
import com.project.thebooklender.utility.*;

public class bookdao {

	public int addBook(Book book) {
		String logHaed = "bookdao.class :: addBook() ";
		int result = -1;
		java.sql.Connection conn = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection("root", "1234");
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "insert into book(book_id,title,author,isbn,category,owner_id) values('"
						+ book.getBook_id() + "','" + book.getTitle() + "','" + book.getAuthor() + "','"
						+ book.getIsbn() + "','" + book.getCategory() + "','" + book.getOwner_id() + "');";
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + "your Data is saved");
				result = st.executeUpdate(sql);
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + "your Data is saved");
			} else {
				System.out.println(logHaed + " DB Connection Not Created :: ");
			}
		} catch (Exception e) {
			result = -2;
			System.out.println(logHaed + " Exception while adding new Books " + e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public Book getBookByID(String id) {
		String logHaed = "bookdao.class :: getBookByID() ";
		int result = -1;
		Book book = new Book();
		book.setBook_id(id);
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection("root", "1234");
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "select * from book where book_id='" + book.getBook_id() + "';";
				rs = st.executeQuery(sql);
				if (rs.next()) {
					book.setBook_id(rs.getString("book_id"));
					book.setTitle(rs.getString("title"));
					book.setAuthor(rs.getString("author"));
					book.setIsbn(rs.getString("isbn"));
					book.setCategory(rs.getString("category"));
					book.setOwner_id(rs.getInt("owner_id"));
				}
				if (st != null)
					st.close();
				st = null;
				if (rs != null)
					rs = null;
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + book.toString());
			} else {
				System.out.println(logHaed + " DB Connection Not Created :: ");
			}
		} catch (Exception e) {
			result = -2;
			System.out.println(logHaed + " Exception while getting book details " + e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return book;
	}

	public List<Book> searchBook(String category, String name) {
		String logHaed = "searchBook.class :: searchBook() ";
		List<Book> booklist = new ArrayList<>();
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {

			conn = dbConn.getDBConnection("root", "1234");
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "select * from book where category=" + category + " and + title=" + name + ";";
				System.out.println(sql);
				rs = st.executeQuery(sql);
				while (rs.next()) {
					Book book = new Book();
					book.setTitle(rs.getString("title"));
					book.setBook_id(rs.getString("book_id"));
					book.setAuthor(rs.getString("author"));
					book.setIsbn(rs.getString("isbn"));
					book.setCategory(rs.getString("category"));
					book.setOwner_id(rs.getInt("owner_id"));

					booklist.add(book);
				}
				if (st != null)
					st.close();
				st = null;
				if (rs != null)
					rs = null;
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + booklist.size());
			} else {
				System.out.println(logHaed + " DB Connection Not Created :: ");
			}
		} catch (Exception e) {
			System.out.println(logHaed + " Exception while searching new books " + e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return booklist;
	}

	public int requestBook(String title, int id) {
		String logHaed = "bookdao.class :: requestBook() ";
		int result = -1;
		List<Integer> list = new ArrayList<>();
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection("root", "1234");
			if (conn != null) {
				Statement st = conn.createStatement();
				// pick owner_id of particular title
				String sql = "select id from book where title=" + title + ";";
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + "your Data is saved");
				rs = st.executeQuery(sql);
				while (rs.next()) {
					list.add(rs.getInt("id"));
				}
				for (Integer n : list) {
					System.out.println(n);
				}
				String sqlInsert = "Insert into requested_by(user_id,book_id) values(?,?)";
				save(list, sqlInsert, id);

			} else {
				System.out.println(logHaed + " DB Connection Not Created :: ");
			}
		} catch (Exception e) {
			result = -2;
			System.out.println(logHaed + " Exception while adding new Books " + e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public void save(List<Integer> list, String SQL_INSERT, int id) throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booklender", "root", "1234");
		try (PreparedStatement pst = conn.prepareStatement(SQL_INSERT);) {
			int i;
			for (i = 0; i < list.size(); i++) {
				pst.setInt(1, id);
				pst.setInt(2, list.get(i));
				pst.executeUpdate();
			}
		}
	}

	public List<String> getOwnerEmail(String title) {
		String logHaed = "bookdao.class :: requestBook() ";
		int result = -1;
		List<Integer> ownerslist = new ArrayList<>();
		List<String> emaillist = new ArrayList<>();
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection("root", "1234");
			if (conn != null) {
				Statement st = conn.createStatement();
				// pick owner_id of particular title
				String sql = "select owner_id from book where title=" + title + ";";
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + "your Data is saved");
				rs = st.executeQuery(sql);
				while (rs.next()) {
					ownerslist.add(rs.getInt("owner_id"));
				}
				for (Integer n : ownerslist) {
					System.out.println(n);
				}
				emaillist = getEmailList(ownerslist);

			} else {
				System.out.println(logHaed + " DB Connection Not Created :: ");
			}
		} catch (Exception e) {
			result = -2;
			System.out.println(logHaed + " Exception while adding new Books " + e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return emaillist;
	}

	public List<String> getEmailList(List<Integer> ownerlist) throws SQLException {
		Connection conn = null;
		List<String> emaillist = new ArrayList<>();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booklender", "root", "1234");
		if (ownerlist.size() > 0) {
			StringBuilder inStatement = new StringBuilder("?");
			for (int i = 1; i < ownerlist.size(); i++) {
				inStatement.append(", ?");
			}
			PreparedStatement ps = conn.prepareStatement("select user_email from users where id in (" + inStatement.toString() + ")");

			int k = 1;
			for (Integer key : ownerlist) {
				ps.setInt(k++, key);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				emaillist.add(rs.getString("user_email"));
			}
		}
		return emaillist;
	}
	
	public int issueBook(Transaction txn) {
		String logHaed = "bookdao.class :: requestBook() ";
		int result = -1;
		List<Integer> list = new ArrayList<>();
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection("root", "1234");
			if (conn != null) {
				Statement st = conn.createStatement();
				// pick owner_id of particular title
				String sql = "insert into transaction(txn_id,txn_date,book_id,lender_id,borrower_id) values ('"
						+ txn.getTxn_id() + "','" + txn.getTxn_date() + "','" + txn.getBook_id() + "','"
						+ txn.getLender_id() + "','" + txn.getBorrower_id() + "');";
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + "your Data is saved");
				result = st.executeUpdate(sql);
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + "your Data is saved");
			} else {
				System.out.println(logHaed + " DB Connection Not Created :: ");
			}
		} catch (Exception e) {
			result = -2;
			System.out.println(logHaed + " Exception while adding new Books " + e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}