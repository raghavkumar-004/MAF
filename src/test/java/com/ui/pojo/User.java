package com.ui.pojo;

public class User {
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	private String email; //name should be same as in the json file where you read the data
	private String password;
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + "]";
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
