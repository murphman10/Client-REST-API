package com.fdmgroup.controller;

import java.net.URI;
import java.util.List;

import com.fdmgroup.model.Contact;
import com.fdmgroup.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/contacts") //the default uri "localhost:8080/api/v1/contacts"
public class ContactController {
	
	private ContactService contactService;
	
	@Autowired
	public ContactController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}
	
	//generate the responses
	
	@GetMapping
	public ResponseEntity<List<Contact>> getContacts() {
		List<Contact> contacts = contactService.retrieveContacts();
		return ResponseEntity.ok(contacts); 
	}
	//we are now returning a response with a body and status
	//@operation and ApiResponse are annotations provided by Swagger
	//localhost:8080/swagger-ui/index.html to view
	
	@Operation(summary = "Gets contacts")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE), 
																		 @Content(mediaType = MediaType.APPLICATION_XML_VALUE)}, 
	headers = {@Header(name = "location", description = "url to location")}, description = "Gets the data"),
	@ApiResponse(responseCode = "404", description = "Contact not found")})
	
	@GetMapping("/{id}")
	public ResponseEntity<Contact> getContact(@PathVariable("id") long contactId) {
		Contact contact = contactService.retrieveContact(contactId);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		//builds the new uri "localhost:8080/api/v1/{id} {id} = contactId"
		
		return ResponseEntity.created(location).body(contact);
		//represents the whole http response, the status code, headers, body
		//this is just another way of doing it, and tying it in with the new uri
	}
	
	@PostMapping
	public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) {
		Contact createdContact = contactService.createContact(contact);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdContact.getContactId()).toUri();
		return ResponseEntity.created(location).body(createdContact);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable("id") long contactId, @RequestBody Contact contact){
		Contact updateContact = contactService.updateContact(contact);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteContact(@PathVariable("id") long contactId) {
		contactService.deleteContact(contactId);
	}
	
}
