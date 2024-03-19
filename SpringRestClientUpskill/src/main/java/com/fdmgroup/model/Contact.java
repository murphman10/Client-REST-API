package com.fdmgroup.model;

import jakarta.validation.constraints.NotBlank;


public class Contact { //our pojo

	private long contactId;
	@NotBlank(message = "First name cannot be blank")
	private String firstName;
	private String middleName;
	private String lastName;
	private String phoneNumber;
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Contact(long contactId, String firstName, String middleName, String lastName, String phoneNumber) {
		super();
		this.contactId = contactId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public long getContactId() {
		return contactId;
	}
	
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
