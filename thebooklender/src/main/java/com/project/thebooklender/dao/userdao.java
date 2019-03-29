package com.project.thebooklender.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public int updateUser(User user) throws SQLException{
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booklender", "root", "1234");
		String sql = "update user set user_name=? and password=? and address=? where id=?";
		ResultSet rs=null;
		try
		{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUser_name());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getAddress());
			pst.setInt(4, user.getId());
			rs=pst.executeQuery();
		}
		 catch(SQLException e)
		 {
		   e.printStackTrace();
		 }
		catch(Exception e) {
			System.out.println("Exception while update new User ");
			
		}
		finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		return 1;	    
}
}
