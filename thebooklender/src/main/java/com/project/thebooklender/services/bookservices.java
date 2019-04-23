package com.project.thebooklender.services;

import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.project.thebooklender.dao.*;
import com.project.thebooklender.bean.*;

@Path("/books")
public class bookservices {
	
	@GET @Path("/show")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		
		System.out.println("hello");
		return "Working....";
	}
	
	@POST
	@Path("/addbook")
	@Produces("application/json")
	@Consumes("application/json")
	public Response addBook(Book book)
	{
		    System.out.println("addbook");
			book.setBook_id(book.getBook_id());
			book.setTitle(book.getTitle());
			book.setAuthor(book.getAuthor());
			book.setIsbn(book.getIsbn());
			book.setCategory(book.getCategory());
			book.setOwner_id(book.getOwner_id());
		
		bookdao dao = new bookdao();
		dao.addBook(book);

		return Response.ok().build();
	} 
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Book getBookByBookId(@PathParam("id") int id)
	{
		bookdao dao = new bookdao();
		return dao.getBookByID(id);
	}
	
	@GET
	@Path("/user/{id}")
	@Produces("application/json")
	public ArrayList<Book> getBookByUserId(@PathParam("id") int id)
	{
		bookdao dao = new bookdao();
		return dao.getBooksByUserId(id);
	}
	
	@GET @Path("/search")
	@Produces("application/json")
	public List<Book> searchBooks(@QueryParam("category") String category, @QueryParam("title") String title)
	{
		bookdao dao = new bookdao();
		return dao.searchBook(category, title);
	}	
	
//	@POST @Path("/requestbook")
//	@Consumes("application/json")
//	public Response requestBook(@QueryParam("title") String title,@QueryParam("requestedby") int id,User user)
//	{
//		bookdao dao =  new bookdao();
//		dao.requestBook(title,id);
//		mailservices m = new mailservices();
//		m.sendmail(title,user);
//		return Response.ok().build();
//	}
	
	@POST @Path("/requestbook")
	@Consumes("application/json")
	public Response requestBook(@QueryParam("id") int bookid,@QueryParam("requestedby") int userid,User user)
	{
		bookdao dao =  new bookdao();
		dao.requestBook(bookid,userid);
		mailservices m = new mailservices();
		m.sendmail(bookid,userid,user);
		return Response.ok().build();
	}
	
	@POST @Path("/issuebook")
	@Consumes("application/json")
	public Response issueBook(@QueryParam("borrowerid") int bid,Book book)
	{
		Calendar calendar = Calendar.getInstance();
		java.sql.Date dt = new java.sql.Date(calendar.getTime().getTime());
		Transaction txn = new Transaction();
		bookdao dao =  new bookdao();
		txn.setTxn_date(dt);
		txn.setBook_id(book.getId());
		txn.setLender_id(book.owner_id);
		txn.setBorrower_id(bid);
		
		int res = dao.issueBook(txn);
		
		if(res==1)
		{
			deleteEntriesFromRequestedBy(book.getId());
		}
		return Response.ok().build();
	}
    
	@GET
	@Path("/requestedby/delete/{id}")	
	@Produces("application/json")
	public int deleteEntriesFromRequestedBy(@PathParam("bookid") int bookid)
	{
		// post accept delete all requests of that book
		bookdao dao = new bookdao();
		return dao.deleteEntriesFromRequestedBy(bookid);
	}
	
	@GET
	@Path("/borrowedpending/{id}")
	@Produces("application/json")
	public List<Book> getPendingRequestsByBorrower(@PathParam("id") int id)
	{
		//get pending requests of a borrower
		bookdao dao = new bookdao();
		List<Integer> list = dao.getPendingRequestsByBorrower(id);
		List<Book> booklist = new ArrayList<>();
		for(Integer i : list)
		{
			Book book = new Book();
			book=dao.getBookByID(i);
			booklist.add(book);
		}
		return booklist;
	}
	
	@GET
	@Path("/lentbooks/{id}")
	@Produces("application/json")
	public List<Book> getLentBooksByUserId(@PathParam("id") int id)
	{
		bookdao dao = new bookdao();
		List<Integer> list = dao.getLentBooksIDsByUserId(id);
		List<Book> booklist = new ArrayList<>();
		for(Integer i : list)
		{
			Book book = new Book();
			book=dao.getBookByID(i);
			booklist.add(book);
		}
		return booklist;
	}
	
	
	@GET
	@Path("/borrowedbooks/{id}")
	@Produces("application/json")
	public List<Book> getBorrowedBooksByUserId(@PathParam("id") int id)
	{
		bookdao dao = new bookdao();
		List<Integer> list = dao.getBorrowBookIDsByUserId(id);
		List<Book> booklist = new ArrayList<>();
		for(Integer i : list)
		{
			Book book = new Book();
			book=dao.getBookByID(i);
			booklist.add(book);
		}
		return booklist;
	}
	
	@GET
	@Path("/requests/{id}")
	@Produces("application/json")
	public List<User> getUsersRequestedBooks(@PathParam("id") int bookid)
	{
		// return all the users who requested particular book
		bookdao dao = new bookdao();
		List<Integer> useridlist = dao.getUsersRequestedBooks(bookid);
		List<User> userdetails = new ArrayList<User>();
 		for(Integer i : useridlist)
		{
			userservices us=new userservices();
			User u = us.getUserbyId(i);
			userdetails.add(u);
		}
		return userdetails;
	}
	
	@GET
	@Path("/requestedby")
	@Produces("application/json")
	public int getUsersRequestedBookID(@QueryParam("bookid") int bookid,@QueryParam("userid") int userid)
	{
		// return all the users who requested particular book
		bookdao dao = new bookdao();
		int requestedbyid = dao.getUsersRequestedBookID(bookid, userid);
		return requestedbyid;
	}
	
	@POST @Path("/cancelbook")
	@Consumes("application/json")
	public Response cancelRequest(@QueryParam("bookid") int bookid,@QueryParam("userid") int userid,User user)
	{
		bookdao dao =  new bookdao();
		dao.cancelRequest(userid,bookid);
		mailservices m = new mailservices();
		m.cancelmail(bookid,userid,user);
		return Response.ok().build();
	}
	
	@POST @Path("/returnbook")
	@Produces("application/json")
	@Consumes("application/json")
	public Response returnRequest(@QueryParam("bookid") int bookid)
	{
		//by borrower
		bookdao dao =  new bookdao();
		Requestlog rl = dao.getBookDetailsFromTranscation(bookid);
		dao.returnBook(rl);
		
		userservices u = new userservices();
        User borrower = u.getUserbyId(rl.getBorrower_id());
		mailservices m = new mailservices();
		m.returnmail(rl.getBook_id(),rl.getBorrower_id(),borrower);
		return Response.ok().build();
	}
	
	@POST @Path("/gotbook")
	@Produces("application/json")
	@Consumes("application/json")
	public Response gotBook(@QueryParam("bookid") int bookid)
	{
		//by lender
		bookdao dao =  new bookdao();
		dao.gotBook(bookid);
		return Response.ok().build();
	}
	
	@POST @Path("/getbookback")
	@Produces("application/json")
	@Consumes("application/json")
	public Response getBookBackRequest(@QueryParam("bookid") int bookid)
	{
		//by borrower
		bookdao dao =  new bookdao();
		Requestlog rl = dao.getBookDetailsFromTranscation(bookid);
		dao.returnBook(rl);
		
		userservices u = new userservices();
        User lender = u.getUserbyId(rl.getLender_id());
		mailservices m = new mailservices();
		m.getBookBackMail(rl.getBorrower_id(),lender);
		return Response.ok().build();
	}
	
	@GET @Path("/checkbuttonReturnBook")
	@Produces("application/json")
	@Consumes("application/json")
	public int checkButtonReturnBook(@QueryParam("bookid") int bookid)
	{
		//by borrower
		bookdao dao =  new bookdao();
		Requestlog rl = new Requestlog();
		rl=dao.checkButtonReturnBook(bookid);
		if(rl.getBook_id()!=bookid)
			return 0;
		return 1;
	}
	
	@GET
	@Path("/allbooks")
	@Produces("application/json")
	public ArrayList<Book> getBooks()
	{
		bookdao dao = new bookdao();
		return dao.getBooks();
	}
	
	@GET 
	@Path("/isexistsInTransaction")
	@Produces("application/json")
	@Consumes("application/json")
	public int isexistsInTransaction(@QueryParam("bookid") int bookid)
	{
		//by borrower
		bookdao dao =  new bookdao();
		Requestlog rl = dao.getBookDetailsFromTranscation(bookid);
		
		System.out.println(rl.getBook_id());
		
		if(rl.getBook_id()!=0)
		{
			return 1;
		}
		return 0; 
	}
//	@GET
//	@Path("/checkexists/{id}")
//	@Produces("application/json")
//	public int checkBooksFromTransaction(@PathParam("id") int bookid)
//	{
//		// return all the users who requested particular book
//		bookdao dao = new bookdao();
//		return dao.checkBooksFromTransaction(bookid);
//	}
//	
}
