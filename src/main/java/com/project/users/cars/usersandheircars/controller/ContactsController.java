package com.project.users.cars.usersandheircars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.users.cars.usersandheircars.dto.ContactDTO;
import com.project.users.cars.usersandheircars.entities.Contacts;
import com.project.users.cars.usersandheircars.services.ContactsService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class ContactsController {
	
	@Autowired
	private ContactsService contactsService;
	
	@PostMapping(value = "/contacts")
	public ResponseEntity<String> saveContact(@Valid @RequestBody ContactDTO contactDTO) {
		String obj = this.contactsService.saveContact(contactDTO);
		return ResponseEntity.ok().body(obj);		
	}
	
	@GetMapping(value = "/contacts")
	public ResponseEntity<List<Contacts>> findContactsAll() {
		List<Contacts> list = this.contactsService.findContactsAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/contacts/{id}")
	public ResponseEntity<Contacts> findById(@PathVariable Long id) {
		var obj = this.contactsService.findContacts(id);
		return ResponseEntity.ok().body(obj);	
	}
	
	@PutMapping(value = "/contacts/{id}")
	public ResponseEntity<String> updateContac(@Valid @RequestBody ContactDTO contactDTO, @PathVariable Long id) {
		String obj = this.contactsService.updateContact(contactDTO, id);
		return ResponseEntity.ok().body(obj);		
	}

}
