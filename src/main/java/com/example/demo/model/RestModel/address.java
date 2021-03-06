package com.example.demo.model.RestModel;

public class address {
	private String doorNo;
	private String street;
	private String place;
	private Long pincode;
	
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	
	@Override
	public String toString() {
		String address="DoorNo : "+this.doorNo+", Street : "+this.street
        +", Place : "+this.place+", Pincode : "+this.pincode;
		return address;
	}
}
