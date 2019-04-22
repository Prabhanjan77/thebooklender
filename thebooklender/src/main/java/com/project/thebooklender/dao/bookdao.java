package com.project.thebooklender.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "insert into book(book_id,title,author,publisher,description,image,isbn,category,owner_id) values('"
						+ book.getBook_id() + "','" + book.getTitle() + "','" + book.getAuthor() + "','" +book.getPublisher() + "','"
						+ book.getDescription() + "','"+  book.getImage() + "','"+book.getIsbn() +  "','" + book.getCategory() + "','" + book.getOwner_id() + "');";
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

	public Book getBookByID(int id) {
		String logHaed = "bookdao.class :: getBookByID() ";
		int result = -1;
		Book book = new Book();
		book.setId(id);
		java.sql.Connection conn = null;  
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "select * from book where id='" + book.getId() + "';";
				rs = st.executeQuery(sql);
				if(rs.next()) {
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

	public ArrayList<Book> getBooksByUserId(int id) {
		String logHaed = "bookdao.class :: getBookByUserId() ";
		int result = -1;
		ArrayList<Book> booklist = new ArrayList<>();
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "select * from book where owner_id='" + id + "';";
				rs = st.executeQuery(sql);
				while (rs.next()) {
					Book book = new Book();
					book.setId(rs.getInt("id"));
					book.setBook_id(rs.getString("book_id"));
					book.setTitle(rs.getString("title"));
					book.setAuthor(rs.getString("author"));
					book.setIsbn(rs.getString("isbn"));
					book.setCategory(rs.getString("category"));
					book.setOwner_id(rs.getInt("owner_id"));
					book.setImage(rs.getString("image"));
					booklist.add(book);
				}
				if (st != null)
					st.close();
				st = null;
				if (rs != null)
					rs = null;
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: ");
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
	
	public List<Book> searchBook(String category, String name) {
		String logHaed = "searchBook.class :: searchBook() ";
		List<Book> booklist = new ArrayList<>();
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {

			conn = dbConn.getDBConnection();
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

//	public int requestBook(String title, int id) {
//		String logHaed = "bookdao.class :: requestBook() ";
//		int result = -1;
//		List<Integer> list = new ArrayList<>();
//		java.sql.Connection conn = null;
//		java.sql.ResultSet rs = null;
//		DBConnection dbConn = new DBConnection();
//		try {
//			conn = dbConn.getDBConnection();
//			if (conn != null) {
//				Statement st = conn.createStatement();
//				// pick owner_id of particular title
//				String sql = "select id from book where title=" + title + ";";
//				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + "your Data is saved");
//				rs = st.executeQuery(sql);
//				while (rs.next()) {
//					list.add(rs.getInt("id"));
//				}
//				for (Integer n : list) {
//					System.out.println(n);
//				}
//				String sqlInsert = "Insert into requested_by(user_id,book_id) values(?,?)";
//				save(list, sqlInsert, id);
//
//			} else {
//				System.out.println(logHaed + " DB Connection Not Created :: ");
//			}
//		} catch (Exception e) {
//			result = -2;
//			System.out.println(logHaed + " Exception while adding new Books " + e);
//
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		return result;
//	}
//
//	public void save(List<Integer> list, String SQL_INSERT, int id) throws SQLException {
//		Connection conn = null;
//		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booklender", "root", "1234");
//		try (PreparedStatement pst = conn.prepareStatement(SQL_INSERT);) {
//			int i;
//			for (i = 0; i < list.size(); i++) {
//				pst.setInt(1, id);
//				pst.setInt(2, list.get(i));
//				pst.executeUpdate();
//			}
//		}
//	}
//
//	public List<String> getOwnerEmail(String title) {
//		String logHaed = "bookdao.class :: requestBook() ";
//		int result = -1;
//		List<Integer> ownerslist = new ArrayList<>();
//		List<String> emaillist = new ArrayList<>();
//		java.sql.Connection conn = null;
//		java.sql.ResultSet rs = null;
//		DBConnection dbConn = new DBConnection();
//		try {
//			conn = dbConn.getDBConnection();
//			if (conn != null) {
//				Statement st = conn.createStatement();
//				// pick owner_id of particular title
//				String sql = "select owner_id from book where title=" + title + ";";
//				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + "your Data is saved");
//				rs = st.executeQuery(sql);
//				while (rs.next()) {
//					ownerslist.add(rs.getInt("owner_id"));
//				}
//				for (Integer n : ownerslist) {
//					System.out.println(n);
//				}
//				emaillist = getEmailList(ownerslist);
//
//			} else {
//				System.out.println(logHaed + " DB Connection Not Created :: ");
//			}
//		} catch (Exception e) {
//			result = -2;
//			System.out.println(logHaed + " Exception while adding new Books " + e);
//
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		return emaillist;
//	}
//
//	public List<String> getEmailList(List<Integer> ownerlist) throws SQLException {
//		Connection conn = null;
//		List<String> emaillist = new ArrayList<>();
//		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booklender", "root", "1234");
//		if (ownerlist.size() > 0) {
//			StringBuilder inStatement = new StringBuilder("?");
//			for (int i = 1; i < ownerlist.size(); i++) {
//				inStatement.append(", ?");
//			}
//			PreparedStatement ps = conn.prepareStatement("select user_email from users where id in (" + inStatement.toString() + ")");
//
//			int k = 1;
//			for (Integer key : ownerlist) {
//				ps.setInt(k++, key);
//			}
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				emaillist.add(rs.getString("user_email"));
//			}
//		}
//		return emaillist;
//	}
	
	public int requestBook(int bookid, int userid) {
		String logHaed = "bookdao.class :: requestBook() ";
		int result = -1;
		List<Integer> list = new ArrayList<>();
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "Insert into requested_by(user_id,book_id) values('"+userid+"','"+bookid+"');";
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + "your Data is saved");
				result = st.executeUpdate(sql);
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
	
	public String getOwnerEmail(int bookid) {
		String logHaed = "bookdao.class :: getOwnerEmail() ";
		int result = -1;
		String emailaddress="";
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "select owner_id from book where id=" + bookid + ";";
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + "your Data is saved");
				rs = st.executeQuery(sql);
				int ownerid=-1;
				if(rs.next())
				{
				  ownerid = rs.getInt("owner_id");
				}
				emailaddress = getEmailAddress(ownerid);

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
		return emailaddress;
	}
	
	public String getEmailAddress(int ownerid) throws SQLException {
		Connection conn = null;
		String emailaddress="";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booklender", "root", "1234");
		PreparedStatement ps = conn.prepareStatement("select user_email from users where id in (" + ownerid + ")");
		ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				emailaddress = rs.getString("user_email");
			}
		return emailaddress;
	}
		
	public int issueBook(Transaction txn) {
		String logHaed = "bookdao.class :: requestBook() ";
		int result = -1;
		List<Integer> list = new ArrayList<>();
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				// pick owner_id of particular title
				String sql = "insert into transaction(txn_date,book_id,lender_id,borrower_id) values ('"+ txn.getTxn_date() + "','" + txn.getBook_id() + "','"
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
	
	public List<Integer> getPendingRequestsByBorrower(int userid) {
		String logHaed = "bookdao.class :: getPendingRequests() ";
		int result = -1;
        ArrayList<Integer> list = new ArrayList<>();
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "select * from requested_by where user_id="+userid+ ";";
				rs = st.executeQuery(sql);
				while (rs.next()) {
					list.add(rs.getInt("book_id"));
				}
				if (st != null)
					st.close();
				st = null;
				if (rs != null)
					rs = null;
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: ");
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

		return list;
	}
	
	public List<Integer> getLentBooksIDsByUserId(int id) {
		String logHaed = "bookdao.class :: getLentBooksByUserId() ";
		int result = -1;
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		List<Integer> list = new ArrayList<Integer>();
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "select book_id from transaction where lender_id=" + id + ";";
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + "your Data is saved");
				rs = st.executeQuery(sql);
				while (rs.next()) {
					list.add(rs.getInt("book_id"));
				}
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
		return list;
	}
	
	public List<Integer> getBorrowBookIDsByUserId(int id) {
		String logHaed = "bookdao.class :: getLentBooksByUserId() ";
		int result = -1;
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<>();
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "select book_id from transaction where borrower_id =" + id + ";";
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: " + "your Data is saved");
				rs = st.executeQuery(sql);
				while (rs.next()) {
					list.add(rs.getInt("book_id"));
				}
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
		return list;
	}
     
	public List<Integer> getUsersRequestedBooks(int bookid) {
		// get users who requested this particular book
		String logHaed = "bookdao.class :: getrequestedBooks() ";
		int result = -1;
		List<Integer> list = new ArrayList<>();
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "select * from requested_by where book_id=" + bookid + ";";
				rs = st.executeQuery(sql);
				while (rs.next()) {
					list.add(rs.getInt("user_id"));
				}
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
		return list;
	}
	
	public int getUsersRequestedBookID(int bookid,int userid) {
		String logHaed = "bookdao.class :: getrequestedBooks() ";
		int result = -1;
	    int ID=-2;
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "select * from requested_by where book_id=" + bookid + " and "+ "user_id= "+ userid +";";
				rs = st.executeQuery(sql);
				System.out.println(sql);
				while (rs.next()) {
					ID=rs.getInt("id");
				}
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
		return ID;
	}
	
	public int deleteEntriesFromRequestedBy(int bookid) {
		String logHaed = "bookdao.class :: deleteEntriesFromRequestedBy() ";
		int result = -1;
		Book book = new Book();
		java.sql.Connection conn = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "delete from requested_by where book_id="+ bookid+";";
				result = st.executeUpdate(sql);
				if (st != null)
					st.close();
				st = null;
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

		return result;
	}
	
	public int cancelRequest(int userid,int bookid) {
		String logHaed = "bookdao.class :: cancelRequest() ";
		int result = -1;
		Book book = new Book();
		java.sql.Connection conn = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "delete from requested_by where user_id=" + userid + " and "+ "book_id= "+ bookid+";";
				System.out.println(sql);
				result = st.executeUpdate(sql);
				if (st != null)
					st.close();
				st = null;
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

		return result;
	}
	
	public Requestlog getBookDetailsFromTranscation(int bookid) {
		String logHaed = "bookdao.class :: returnBook() ";
		int result = -1;
		Book book = new Book();
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		Requestlog rl = new Requestlog();
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "select * from transaction where book_id="+bookid+";";
				System.out.println(sql);
				rs= st.executeQuery(sql);
				while (rs.next()) {
				    rl.setBook_id(rs.getInt("book_id"));
				    rl.setLender_id(rs.getInt("lender_id"));
				    rl.setBorrower_id(rs.getInt("borrower_id"));
				}
				if (st != null)
					st.close();
				st = null;
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

		return rl;
	}
	
	public int returnBook(Requestlog rl) {
		String logHaed = "bookdao.class :: returnBook() ";
		int result = -1;
		Book book = new Book();
		java.sql.Connection conn = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "Insert into requestlog(book_id,lender_id,borrower_id) values('"+rl.getBook_id()+"','"+rl.getLender_id()+"','"+rl.getBorrower_id()+"');";
				System.out.println(sql);
				result = st.executeUpdate(sql);
				if (st != null)
					st.close();
				st = null;
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

		return result;
	}
	
	public int gotBook(int bookid) {
		String logHaed = "bookdao.class :: returnBook() ";
		int result1 = -1;
		int result2 = -1;
		int res = 0;
		Book book = new Book();
		java.sql.Connection conn = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql1 = "delete from requestlog where book_id = " + bookid +";";
				String sql2 = "delete from transaction where book_id = " + bookid +";";
				//System.out.println(sql);
				result1 = st.executeUpdate(sql1);
				result2 = st.executeUpdate(sql2);
				if (st != null)
					st.close();
				st = null;
				System.out.println(logHaed + " SQL :: " + sql1 + " :: Result :: " + book.toString());
			} else {
				System.out.println(logHaed + " DB Connection Not Created :: ");
			}
		} catch (Exception e) {
			result1 = -2;
			result1 = -2;
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
		if(result1==1&&result2==1) res=1;
        
	  return res;
	}
	
	public int getBookBack(Requestlog rl) {
		String logHaed = "bookdao.class :: returnBook() ";
		int result = -1;
		Book book = new Book();
		java.sql.Connection conn = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "Insert into requestlog(book_id,lender_id,borrower_id) values('"+rl.getBook_id()+"','"+rl.getLender_id()+"','"+rl.getBorrower_id()+"');";
				System.out.println(sql);
				result = st.executeUpdate(sql);
				if (st != null)
					st.close();
				st = null;
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
		return result;
	}
	
	public Requestlog checkButtonReturnBook(int bookid) {
		String logHaed = "bookdao.class :: returnBook() ";
		int result = -1;
		Book book = new Book();
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		Requestlog rl= new Requestlog();
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "select * from requestlog where book_id="+bookid;
				rs= st.executeQuery(sql);
				if (rs.next()) {
				    rl.setBook_id(rs.getInt("book_id"));
				    rl.setLender_id(rs.getInt("lender_id"));
				    rl.setBorrower_id(rs.getInt("borrower_id"));
				}
				if (st != null)
					st.close();
				st = null;
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
		return rl;
	}
	
	public ArrayList<Book> getBooks() {
		String logHaed = "bookdao.class :: getBookByUserId() ";
		int result = -1;
		ArrayList<Book> booklist = new ArrayList<>();
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		DBConnection dbConn = new DBConnection();
		try {
			conn = dbConn.getDBConnection();
			if (conn != null) {
				Statement st = conn.createStatement();
				String sql = "select * from book;";
				rs = st.executeQuery(sql);
				while (rs.next()) {
					Book book = new Book();
					book.setId(rs.getInt("id"));
					book.setBook_id(rs.getString("book_id"));
					book.setTitle(rs.getString("title"));
					book.setAuthor(rs.getString("author"));
					book.setIsbn(rs.getString("isbn"));
					book.setCategory(rs.getString("category"));
					book.setOwner_id(rs.getInt("owner_id"));
					book.setImage(rs.getString("image"));
					booklist.add(book);
				}
				if (st != null)
					st.close();
				st = null;
				if (rs != null)
					rs = null;
				System.out.println(logHaed + " SQL :: " + sql + " :: Result :: ");
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