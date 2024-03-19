package com.fdmgroup.repository;

import com.fdmgroup.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long>{
	
}
