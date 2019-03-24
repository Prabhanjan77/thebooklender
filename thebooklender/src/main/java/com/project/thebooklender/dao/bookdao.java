package com.project.thebooklender.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
				String sql = "insert into book(book_id,title,author,isbn,category,owner_id,lent_to) values('"
						+ book.getBook_id() + "','" + book.getTitle() + "','" + book.getAuthor() + "','" + book.getIsbn()
						+ "','" + book.getCategory() + "','" + book.getOwner_id() + "','" + book.getLent_to() + "');";
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
		String logHaed="bookdao.class :: getBookByID() ";
		int result=-1;
		Book book=new Book();
		book.setBook_id(id);
		java.sql.Connection conn=null;
		java.sql.ResultSet rs=null;
		DBConnection dbConn=new DBConnection();
		try {
			conn=dbConn.getDBConnection("root", "1234");
			if(conn!=null) {
			Statement st=conn.createStatement();
			String sql="select * from book where book_id='"+book.getBook_id()+"';";
			rs=st.executeQuery(sql);
			if(rs.next()) {
				book.setBook_id(rs.getString("book_id"));			
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setIsbn(rs.getString("isbn"));
				book.setCategory(rs.getString("category"));
				book.setOwner_id(rs.getInt("owner_id"));
				book.setLent_to(rs.getInt("lent_to"));
			}
			if(st!=null)st.close();
			st=null;
			if(rs!=null)rs=null;
				System.out.println(logHaed+" SQL :: "+sql+" :: Result :: "+book.toString());
			}else {
				System.out.println(logHaed+" DB Connection Not Created :: ");
			}	
		}catch(Exception e) {
			result=-2;
			System.out.println(logHaed+" Exception while getting book details "+e);
			
		}finally {
			if(conn!=null) {
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
	
	public List<Book> searchBook(String category,String name) {
		String logHaed="searchBook.class :: searchBook() ";
		List<Book> booklist=new ArrayList<>();
		java.sql.Connection conn=null;
		java.sql.ResultSet rs=null;
		DBConnection dbConn=new DBConnection();
		try {
			
			conn=dbConn.getDBConnection("root", "1234");
			if(conn!=null) {
			Statement st=conn.createStatement();
			String sql="select * from book where category="+category+" and + title="+name+";";
			System.out.println(sql);
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Book book=new Book();
				book.setTitle(rs.getString("title"));				
				book.setBook_id(rs.getString("book_id"));
				book.setAuthor(rs.getString("author"));
				book.setIsbn(rs.getString("isbn"));
				book.setCategory(rs.getString("category"));
				book.setOwner_id(rs.getInt("owner_id"));
				book.setLent_to(rs.getInt("lent_to"));
		
				booklist.add(book);
			}
			if(st!=null)st.close();
			st=null;
			if(rs!=null)rs=null;
				System.out.println(logHaed+" SQL :: "+sql+" :: Result :: "+booklist.size());
			}else {
				System.out.println(logHaed+" DB Connection Not Created :: ");
			}
		}catch(Exception e) {
			System.out.println(logHaed+" Exception while searching new books "+ e);
			
		}finally {
			if(conn!=null) {
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