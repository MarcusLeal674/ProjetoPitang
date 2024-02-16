package com.project.users.cars.usersandheircars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.users.cars.usersandheircars.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {	
	boolean existsByEmail(String email);
	boolean existsByLogin(String login);
}
