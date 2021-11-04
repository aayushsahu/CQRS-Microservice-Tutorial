package com.solinvictus.Products.Models;

public class Address {

	private String houseNo;
	private String landmark;
	private String street;
	private String area;
	private String city;
	private String state;
	private int pinCode;
	
	public Address() {
	}
	
	public Address(String houseNo, String landmark, String street, String area, String city, String state,
			int pinCode) {
		super();
		this.houseNo = houseNo;
		this.landmark = landmark;
		this.street = street;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

}
