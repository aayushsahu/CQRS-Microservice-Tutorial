package com.solinvictus.Products.Models;

public class User {
	
	private Long userId;
	
	private String name;
	private String email;
	private String password;
	private int age;
	
	private Address address;
	
	
	public User() {
	}
	
	public User(Long userId, String name, String email, String password, int age, Address address) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
		this.address = address;
	}

	public Long getId() {
		return userId;
	}
	
	public void setId(Long userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
