package com.project.thebooklender.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import com.project.thebooklender.bean.*;
import com.project.thebooklender.utility.*;

public class userdao {
	public int addUser(User user) {
		String logHaed="userdao.class :: addUser() ";
		int result=-1;
		java.sql.Connection conn=null;
		DBConnection dbConn=new DBConnection();
		try {
			conn=dbConn.getDBConnection("root", "1234");
			if(conn!=null) {
			Statement st=conn.createStatement();
			String sql="insert into user(user_name,user_email,password,address) values('"+user.getUser_name()+"','"+user.getUser_email()+"','"+user.getPassword()+"','"+user.getAddress()+"');";
			System.out.println(logHaed+" SQL :: "+sql+" :: Result :: "+"your Data is saved");
			result=st.executeUpdate(sql);
				System.out.println(logHaed+" SQL :: "+sql+" :: Result :: "+"your Data is saved");
			}else {
				System.out.println(logHaed+" DB Connection Not Created :: ");
			}
		}catch(Exception e) {
			result=-2;
			System.out.println(logHaed+" Exception while adding new User "+e);
			
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		return result;	    
}
}
