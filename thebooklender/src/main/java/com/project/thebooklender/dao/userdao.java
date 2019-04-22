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
		String logHaed="userdao.class :: addUser()";
		int result=-1;
		java.sql.Connection conn=null;
		DBConnection dbConn=new DBConnection();
		try {
			conn=dbConn.getDBConnection();
			if(conn!=null) {
			Statement st=conn.createStatement();
			String sql="insert into users(user_name,user_email,password,address) values('"+user.getUser_name()+"','"+user.getUser_email()+"','"+user.getPassword()+"','"+user.getAddress()+"');";
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
	
	public int updateUser(User user){
		int result=-1;
		java.sql.Connection conn=null;
		DBConnection dbConn=new DBConnection();
		try
		{
			conn=dbConn.getDBConnection();
			if(conn!=null) {
			Statement st=conn.createStatement();
			String sql="update users set user_name='"+user.getUser_name()+"',"+"password='"+user.getPassword()+"',"+"address= '"+ user.getAddress()+"'"+" where id="+user.getId()+";";
			System.out.println("SQL::"+sql+"your Data is saved");
			result=st.executeUpdate(sql);
				System.out.println("SQL::"+sql+" :: your Data is saved");
			}else {
				System.out.println("DB Connection Not Created in updateUser");
			}
		}catch(Exception e) {
			result=-2;
			System.out.println("Exception while updating a User "+e);
			
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
	
	public User getUserByEmail(User user) {
		String logHaed="userdao.class :: getUserByEmail()";
		int result=-1;
		java.sql.Connection conn=null;
		java.sql.ResultSet rs = null;
		User newuser=new User();
		DBConnection dbConn=new DBConnection();
		try {
			conn=dbConn.getDBConnection();
			if(conn!=null) {
			Statement st=conn.createStatement();
			String sql="select * from users where user_email='" + user.getUser_email() + "';";
			System.out.println(logHaed+" SQL :: "+sql+" :: Result :: "+"your Data is saved");
			rs=st.executeQuery(sql);
			if (rs.next()) {
				newuser.setId(rs.getInt("id"));
				newuser.setUser_name(rs.getString("user_name"));
				newuser.setUser_email(rs.getString("user_email"));
				newuser.setPassword(rs.getString("password"));
				System.out.println(user.getId());
				System.out.println(user.getUser_name());
			}
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
		return newuser;	    
}
	
	public User getUserById(int id) {
		String logHaed="userdao.class :: getUserById()";
		int result=-1;
		java.sql.Connection conn=null;
		java.sql.ResultSet rs = null;
		User user = new User(); 
		DBConnection dbConn=new DBConnection();
		try {
			conn=dbConn.getDBConnection();
			if(conn!=null) {
			Statement st=conn.createStatement();
			String sql="select * from users where id='" + id + "';";
			System.out.println(logHaed+" SQL :: "+sql+" :: Result :: "+"your Data is saved");
			rs=st.executeQuery(sql);
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_email(rs.getString("user_email"));
				user.setPassword(rs.getString("password"));
				System.out.println(user.getId());
				System.out.println(user.getUser_name());
			}
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
		return user;	    
}
	public Boolean validateUsername(String email) 
	{
		String logHaed="userdao.class :: getUserByEmail()";
		Boolean result=false;
		java.sql.Connection conn=null;
		java.sql.ResultSet rs = null;
		//User newuser=new User();
		DBConnection dbConn=new DBConnection();
		try {
			conn=dbConn.getDBConnection();
			if(conn!=null) {
			Statement st=conn.createStatement();
			String sql="select * from users where user_email='" + email + "';";
			System.out.println(logHaed+" SQL :: "+sql+" :: Result :: "+"your Data is saved");
			rs=st.executeQuery(sql);
			while(rs.next()) {
				result=true;
				
			}
			System.out.println(logHaed+" SQL :: "+sql+" :: Result :: "+"your Data is saved");
			}else {
				System.out.println(logHaed+" DB Connection Not Created :: ");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
}
