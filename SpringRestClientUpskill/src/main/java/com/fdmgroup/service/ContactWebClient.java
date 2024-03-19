package com.fdmgroup.service;

import java.util.List;

import com.fdmgroup.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class ContactWebClient implements ContactRestClient {
	
	private WebClient contactRestWebClient;
	
	@Autowired
	public ContactWebClient(WebClient contactRestWebClient) {
		super();
		this.contactRestWebClient = contactRestWebClient;
	}

	@Override
	public List<Contact> getContact() {
		// TODO Auto-generated method stub
		return contactRestWebClient.get().retrieve().bodyToFlux(Contact.class).collectList().block();
		//this is how we call the API (SpringRestUpskill)
	}

	@Override
	public Contact getContact(long id) {
		// TODO Auto-generated method stub
		return contactRestWebClient.get()
				.uri("/"+id)
				.retrieve()
				.onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
				response -> Mono.error(new ContactNotFound("Contact" + id + "not found")))
				.bodyToMono(Contact.class).block();
		//how we consume the api for a particular contact ID
	}

	@Override
	public Contact createContact(Contact contact) {
		// TODO Auto-generated method stub
		return contactRestWebClient.post().bodyValue(contact)
				.retrieve()
				.bodyToMono(Contact.class)
				.block();
	}

	@Override
	public Contact updateContact(long id, Contact contact) {
		// TODO Auto-generated method stub
		return contactRestWebClient.put()
				.uri("/"+contact.getContactId())
				.bodyValue(contact)
				.retrieve()
				.bodyToMono(Contact.class)
				.block();
	}

	@Override
	public void deleteContact(long id) {
		// TODO Auto-generated method stub
		contactRestWebClient.delete()
		.uri("/"+id)
		.retrieve()
		.toBodilessEntity()
		.block();
	}
	
	
}
