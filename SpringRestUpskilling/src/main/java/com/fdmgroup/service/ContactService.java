package com.fdmgroup.service;

import java.util.List;
import java.util.Optional;

import com.fdmgroup.model.Contact;
import com.fdmgroup.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
	
	private ContactRepository contactRepo;
	
	@Autowired
	public ContactService(ContactRepository contactRepo) {
		super();
		this.contactRepo = contactRepo;
	}
	
	public List<Contact> retrieveContacts() {
		return contactRepo.findAll();
	}
	
	public Contact retrieveContact(long contactId) {
		Optional<Contact> contactOpt= contactRepo.findById(contactId);
		if(!contactOpt.isPresent()) {
			throw new ContactNotFoundException("Contact with id " + contactId + "was not found");
		}
		return contactOpt.get();
	}
	
	public Contact createContact(Contact contact) {
		return contactRepo.save(contact);
	}
	
	//updates if it already exists, checks Primary key
	public Contact updateContact(Contact contact) {
		return contactRepo.save(contact);
	}
	
	public void deleteContact(long contactId) {
		contactRepo.deleteById(contactId);
	}
	
	
	
}
