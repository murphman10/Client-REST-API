package com.fdmgroup.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends RuntimeException {

	public ContactNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


	
	
}

//A custom exception class
