package com.project.thebooklender.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.thebooklender.bean.Book;
import com.project.thebooklender.dao.carouseldao;

@Path("carousel")
public class carouselservice {
	//private CaroselService caroselService=new CaroselService();
	@GET
	@Path("/getBooksByCategory/{category}")
	@Produces(MediaType.APPLICATION_JSON)
   public ArrayList<Book> getBooksByCategory(@PathParam("category") String category){
     System.out.println("ch"+category);
	carouseldao cdao = new carouseldao();
	return cdao.getBooksByCategory(category);
}
}