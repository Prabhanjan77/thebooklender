package com.project.thebooklender.bean;

public class User {
	public User() {}
     public int id;
     public String user_email;
     public String user_name;
     public String password;
     public String address;
     
     public User(int id,String user_email,String user_name,String password,String address)
     {
    	 super();
    	 this.id=id;
    	 this.user_email=user_email;
    	 this.user_name=user_name;
    	 this.password=password;
    	 this.address=address;
     }

    

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUser_email() {
		return user_email;
	}



	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}



	public String getUser_name() {
		return user_name;
	}



	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", user_email=" + user_email + ", user_name=" + user_name + ", password=" + password
				+ ", address=" + address + "]";
	}
}
