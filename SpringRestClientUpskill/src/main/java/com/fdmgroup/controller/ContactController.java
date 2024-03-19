package com.fdmgroup.controller;

import java.util.List;

import com.fdmgroup.model.Contact;
import com.fdmgroup.service.ContactNotFound;
import com.fdmgroup.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {
	
	private ContactService contactService;
	
	@Autowired
	public ContactController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}
	
	@GetMapping
	public String getContacts(Model model) {
		List<Contact> contacts = contactService.getContact();
		model.addAttribute("contacts", contacts);
		return "list-contact";
	}
	
	@GetMapping("/{id}")
	public String getContact(@PathVariable long id, Model model) {
		Contact contact = contactService.getContact(id);
		model.addAttribute("contact", contact);
		return "details-contact";
	}
	
	@GetMapping("/createContact")
	public String createContact(Model model) {
		model.addAttribute("contact", new Contact());
		return "create-contact";
	}
	
	@PostMapping("/createContact")
	public String saveContact(Contact contact, Model model) {
		
		Contact savedContact = contactService.createContact(contact);
		return "redirect:/"+savedContact.getContactId();
	}
	
	@GetMapping("/delete/{id}")
	public String deleteContact(@PathVariable("id") long contactId) {
		contactService.deleteContact(contactId);
		return "redirect:/";
	}
	
	@GetMapping("/update/{id}")
	public String updateContact(@PathVariable("id") long contactId, Model model) {
		//get contact first
		Contact contact = contactService.getContact(contactId);
		model.addAttribute("contact", contact);
		return "update-contact";
		
	}
	
	@PostMapping("/updateContact/{id}")
	public String updateContact(@PathVariable("id") long contactId, Contact contact) {
		contactService.putContact(contactId, contact);
		return "redirect:/";
	}
	
	@ExceptionHandler(ContactNotFound.class)
	public String handleContactNotFound(ContactNotFound cnfe, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("error message", cnfe.getMessage());
		return "redirect:/";
	}

}
