package com.project.users.cars.usersandheircars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.users.cars.usersandheircars.entities.Contacts;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {
	boolean existsByCellPhone(String cellPhone);
}
