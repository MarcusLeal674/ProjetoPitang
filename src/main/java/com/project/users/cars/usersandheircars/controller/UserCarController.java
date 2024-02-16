package com.project.users.cars.usersandheircars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.users.cars.usersandheircars.dto.UsersDTO;
import com.project.users.cars.usersandheircars.entities.Users;
import com.project.users.cars.usersandheircars.services.UsersCarsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class UserCarController {
	
	@Autowired
	private UsersCarsService usersService;
	
	@PostMapping(value = "/users")
	public ResponseEntity<String> saveUserCar(@Valid @RequestBody UsersDTO usersDTO) {
		String obj = usersService.saveUserCars(usersDTO);
		return ResponseEntity.ok().body(obj);		
	} 
	
	@GetMapping(value = "/users")
	public ResponseEntity<List<Users>> findAll() {
		List<Users> list = usersService.findUsersAll();
		return ResponseEntity.ok().body(list);
	}
	
	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		usersService.DeleteUser(id);
		return ResponseEntity.ok().body("User deleted successfully");	
	}
	
	@GetMapping(value = "/users/{id}")
	public ResponseEntity<Users> findById(@PathVariable Long id) {
		var obj = usersService.findUser(id);
		return ResponseEntity.ok().body(obj);	
	} 
	
	@PutMapping(value = "/users/{id}")
	public ResponseEntity<String> updateUserCar(@Valid @RequestBody UsersDTO usersDTO, @PathVariable Long id) {
		String obj = usersService.updateUserCars(usersDTO, id);
		return ResponseEntity.ok().body(obj);		
	}

}
