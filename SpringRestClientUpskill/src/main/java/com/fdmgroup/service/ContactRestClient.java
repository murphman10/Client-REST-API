package com.fdmgroup.service;

import java.util.List;

import com.fdmgroup.model.Contact;

public interface ContactRestClient {
	
	List<Contact> getContact();
	Contact getContact(long id);
	Contact createContact(Contact contact);
	Contact updateContact(long id, Contact contact);
	void deleteContact(long id);
}
