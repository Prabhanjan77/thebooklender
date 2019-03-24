package com.project.thebooklender.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
			book.setLent_to(book.getLent_to());
		
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
}
