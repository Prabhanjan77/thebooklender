package test;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;
import com.project.thebooklender.services.bookservices;
import com.project.thebooklender.services.userservices;

public class UserTest extends JerseyTest {
	@Override
    protected Application configure() {
        return new ResourceConfig(userservices.class);
    }
	
    @Test
    public void userbyid() {
        String response = target("users/2").request().get(String.class);
        System.out.println(response.substring(63, 89));
        Assert.assertTrue("prabhanjan.reddy@iiitb.org".equals(response.substring(63, 89)));
    }
}
