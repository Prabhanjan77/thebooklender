package com.project.thebooklender.services;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.project.thebooklender.dao.*;
import com.project.thebooklender.bean.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class mailservices {

	    public void sendmail(String title) {
            bookdao dao=new bookdao();
           // List<String> emaillist = new ArrayList<String>();
            List<String> recipientList = dao.getOwnerEmail(title);
            
	        final String username = "prabhareddy1016@gmail.com";
	        final String password = " ";  //removed password for now.

	        Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(props,
	          new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	          });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("prabhareddy1016@gmail.com"));
	           // String[] recipientList = emaillist.split();
	            InternetAddress[] recipientAddress = new InternetAddress[recipientList.size()];
	            int counter = 0;
	            for (String recipient : recipientList) {
	                recipientAddress[counter] = new InternetAddress(recipient.trim());
	                counter++;
	            }
	            message.setRecipients(Message.RecipientType.TO, recipientAddress);
	          //  message.setRecipients(Message.RecipientType.TO,
	           //     InternetAddress.parse("mentlaprabhanjanreddy@gmail.com"));
	            message.setSubject("Testing Subject");
	            message.setText("Dear Mail Crawler,"
	                + "\n\n No spam to my email, please!");

	            Transport.send(message);

	            System.out.println("Done");

	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
	    }
	}
