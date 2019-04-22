package com.project.thebooklender.services;
import java.sql.SQLException;
import java.util.List;

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

@Path("/users")
public class userservices {
	
	@GET @Path("/show")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		
		
		return "Working user part....";
	}
	
	@POST
	@Path("/add")
	@Produces("application/json")
	@Consumes("application/json")
	public Response addUser(User user)
	{
		    System.out.print("add user");
			user.setUser_name(user.getUser_name());
			user.setUser_email(user.getUser_email());
			user.setPassword(user.getPassword());
			user.setAddress(user.getAddress());
		
		userdao dao = new userdao();
		int response = dao.addUser(user);
        if(response!=1)  
          return Response.status(Response.Status.NOT_FOUND).entity("Resource not found for ID: " + user.user_email).build();
		return Response.ok().build();
	} 
	
	@PUT
	@Path("/update")
	@Consumes("application/json")
	public Response updateUser(@QueryParam("userid") int id,User user)
	{
		    if(!(user.getId()>0)) user.setId(id);
		    System.out.print("update user");
			user.setUser_name(user.getUser_name());
			user.setPassword(user.getPassword());
			user.setAddress(user.getAddress());
		
			userdao dao = new userdao();
		    dao.updateUser(user);
		
		return Response.ok().build();
		}
	
	@POST
	@Path("/email")
	@Consumes("application/json")
	@Produces("application/json")
	public User getUserbyEmail(User user) {
		System.out.print("c "+user.getPassword());
		userdao dao = new userdao();
		User user_details = dao.getUserByEmail(user);
		System.out.println("getUserbyEmail(): user: " + user);
		System.out.println("getUserbyEmail(): user_details: " + user_details);
		if (user_details == null)
		{
		  User newUser = new User();
		  newUser.setUser_email(null);
		  return null;
		}
		System.out.print("c "+user.getPassword());
		System.out.print("d "+user_details.getPassword());
		if (!(user.getPassword()).equals(user_details.getPassword()))
			return null;
		else
			return user_details;
	}
	
	@GET
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public User getUserbyId(@PathParam("id") int id) {
		userdao dao = new userdao();
		User user_details = dao.getUserById(id);
		return user_details;
	}
	
	@POST
	@Path("validate")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean validate(String email) {
		userdao dao = new userdao();
		System.out.println(email);
		boolean b=dao.validateUsername(email); 
		System.out.println("validate");
		return b;
	}
} 

