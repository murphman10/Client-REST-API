package com.fdmgroup.service;

import java.util.List;

import com.fdmgroup.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
	
	private ContactRestClient contactClient;
	//injecting the interface -> loose coupling (will find the concretion defined in ContactWebClient)
	
	@Autowired
	public ContactService(ContactRestClient contactClient) {
		super();
		this.contactClient = contactClient;
	}
	
	public List<Contact> getContact() {
		
		return contactClient.getContact();
	}
	
	public Contact getContact(long id) {
		
		return contactClient.getContact(id);
	}
	
	public Contact createContact(Contact contact) {
		return contactClient.createContact(contact);
	}
	
	public Contact putContact(long id, Contact contact) {
		
		return contactClient.updateContact(id, contact);
	}
	
	public void deleteContact(long id) {
		
		contactClient.deleteContact(id);
	}
	
	
}
