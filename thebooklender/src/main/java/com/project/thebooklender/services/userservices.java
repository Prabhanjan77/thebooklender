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
	@Path("/adduser")
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
		dao.addUser(user);

		return Response.ok().build();
	} 
	
	@POST
	@Path("/updateuser")
	@Produces("application/json")
	@Consumes("application/json")
	public Response updateUser(User user,int id)
	{
		    System.out.print("update user");
			user.setUser_name(user.getUser_name());
			user.setUser_email(user.getUser_email());
			user.setPassword(user.getPassword());
			user.setAddress(user.getAddress());
		
		userdao dao = new userdao();
		try{
			dao.updateUser(user);
			} 
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}     
		return Response.ok().build();
		}
	} 
