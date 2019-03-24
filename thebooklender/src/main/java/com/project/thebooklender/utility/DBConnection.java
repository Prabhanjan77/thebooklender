package com.project.thebooklender.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
		
		public DBConnection() {}
		
		public java.sql.Connection getDBConnection(String user,String pass){
			String logHead="DBConnection.class :: getDBConnection() :: ";
			Connection con=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/booklender", user, pass);
				System.out.println(logHead+" Mysql Connection Created Successfully ..");
			}catch(Exception e) {
				System.out.println(logHead+" Exception while Creating mysql Conection .. "+e);
			}
			return con;
		}
}
