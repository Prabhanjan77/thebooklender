package test;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import javax.ws.rs.core.Application;

import com.project.thebooklender.bean.Book;
import com.project.thebooklender.services.bookservices;
import com.project.thebooklender.services.userservices;

public class BookTest extends JerseyTest {
	@Override
    protected Application configure() {
        return new ResourceConfig(bookservices.class);
    }
	
    @Test
    public void bookbyid() {
        Book response = target("books/3").request().get(Book.class);
        Assert.assertTrue("Pride and Prejudice".equals(response.getTitle()));
    }
    
    @Test
    public void bookbyuserid() {
        String response = target("books/user/2").request().get(String.class);
        System.out.println(response);
        Assert.assertTrue(response.contains("Pride and Prejudice"));
        Assert.assertTrue(response.contains("Introduction to Algorithms"));
    }
}
