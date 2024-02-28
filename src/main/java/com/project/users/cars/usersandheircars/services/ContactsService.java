package com.project.users.cars.usersandheircars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.users.cars.usersandheircars.dto.ContactDTO;
import com.project.users.cars.usersandheircars.entities.Contacts;
import com.project.users.cars.usersandheircars.repositories.ContactsRepository;

@Service
public class ContactsService {
	
	@Autowired
	private ContactsRepository contactsRepository;
	
	public String saveContact(ContactDTO contactDTO) {
		if (this.validadeCellPhone(contactDTO.getCellPhone())) {
			return "CellPhone exists";
		}		
		this.contactsRepository.save(this.setContact(contactDTO));
		return "Contact successfully registered";
	}
	
	public List<Contacts> findContactsAll() {
		return this.contactsRepository.findAll();
	}
	
	public Contacts findContacts(Long id) {
		return this.contactsRepository.findById(id).get();
	}
	
	public String updateContact(ContactDTO contactDTO, Long id) {
		Contacts contacts = this.findContacts(id);
		contacts.setCellPhone(contactDTO.getCellPhone());
		contacts.setEmail(contactDTO.getEmail());
		contacts.setFavorite(contactDTO.getFavorite());
		contacts.setName(contactDTO.getName());
		contacts.setSurname(contactDTO.getSurname());
		this.contactsRepository.save(contacts);
		
		return "Contacts updated successfully";
	}
	
	private boolean validadeCellPhone(String cellPhone) {
		return this.contactsRepository.existsByCellPhone(cellPhone);
	}
	
	private Contacts setContact(ContactDTO contactDTO) {
		var contacts = new Contacts();
		contacts.setName(contactDTO.getName());
		contacts.setSurname(contactDTO.getSurname());
		contacts.setEmail(contactDTO.getEmail());
		contacts.setCellPhone(contactDTO.getCellPhone());
		contacts.setFavorite(contactDTO.getFavorite());
		
		return contacts;
	}

}
