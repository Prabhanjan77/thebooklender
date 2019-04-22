package com.project.thebooklender.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.thebooklender.bean.Book;
import com.project.thebooklender.utility.DBConnection;

public class carouseldao {
	public ArrayList<Book>  getBooksByCategory(String category) {
		ArrayList<Book> booklist=new ArrayList<Book>();
		String logHaed = "bookdao.class :: getBooksByCategory() ";		
		int result = -1;
		java.sql.Connection conn = null;  
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "select * from book where category=" + category + ";";
				System.out.println("h"+sql);
				rs = st.executeQuery(sql);
				while (rs.next()) {
					Book book = new Book();
					book.setId(rs.getInt("id"));
					book.setBook_id(rs.getString("book_id"));
					book.setTitle(rs.getString("title"));
					book.setAuthor(rs.getString("author"));
					book.setIsbn(rs.getString("isbn"));
					book.setCategory(rs.getString("category"));
					book.setPublisher(rs.getString("publisher"));
					book.setImage(rs.getString("image"));
					book.setDescription(rs.getString("description"));
					book.setOwner_id(rs.getInt("owner_id"));
					System.out.println("ftg "+ book.getImage());
					System.out.println(book.getDescription());
					booklist.add(book);
				}
				if (st != null)
					st.close();
				st = null;
				if (rs != null)
					rs = null;
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + booklist.toString());
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

		return booklist;
	}
}