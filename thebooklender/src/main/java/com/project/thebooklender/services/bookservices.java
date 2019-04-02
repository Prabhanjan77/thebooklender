package com.project.thebooklender.services;

import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	public Book getBookByBookId(@PathParam("id") String id)
	{
		System.out.println("1");
		bookdao dao = new bookdao();
		return dao.getBookByID(id);
	}
	
	@GET @Path("/search")
	@Produces("application/json")
	public List<Book> searchBooks(@QueryParam("category") String category, @QueryParam("title") String title)
	{
		bookdao dao = new bookdao();
		return dao.searchBook(category, title);
	}	
	
	@POST @Path("/requestbook")
	@Consumes("application/json")
	public Response requestBook(@QueryParam("title") String title,@QueryParam("requestedby") int id,User user)
	{
		System.out.println("req");
		bookdao dao =  new bookdao();
		dao.requestBook(title,id);
		mailservices m = new mailservices();
		m.sendmail(title,user);
		return Response.ok().build();
	}
	
	@POST @Path("/issuebook")
	@Consumes("application/json")
	public Response issueBook(@QueryParam("transaction") int txn_id,@QueryParam("borrowerid") int bid,Book book)
	{
		 Calendar calendar = Calendar.getInstance();
		 java.sql.Date dt = new java.sql.Date(calendar.getTime().getTime());
		Transaction txn = new Transaction();
		bookdao dao =  new bookdao();
		txn.setTxn_id(txn_id);
		txn.setTxn_date(dt);
		txn.setBook_id(book.getId());
		txn.setLender_id(book.owner_id);
		txn.setBorrower_id(bid);
		
		dao.issueBook(txn);
		return Response.ok().build();
	}
}
