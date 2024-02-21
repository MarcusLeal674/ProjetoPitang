package com.project.users.cars.usersandheircars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.users.cars.usersandheircars.dto.AuthenticationUserDTO;
import com.project.users.cars.usersandheircars.dto.LoginTokenResponseDTO;
import com.project.users.cars.usersandheircars.dto.UsersDTO;
import com.project.users.cars.usersandheircars.entities.Users;
import com.project.users.cars.usersandheircars.security.TokenUserService;
import com.project.users.cars.usersandheircars.services.UsersService;
import com.project.users.cars.usersandheircars.utils.UserCarUtility;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenUserService tokenUserService;
	
	@PostMapping(value = "/users")
	public ResponseEntity<String> saveUserCar(@Valid @RequestBody UsersDTO usersDTO) {
		String obj = this.usersService.saveUserCars(usersDTO);
		return ResponseEntity.ok().body(obj);		
	} 
	
	@GetMapping(value = "/users")
	public ResponseEntity<List<Users>> findUsersAll() {
		List<Users> list = this.usersService.findUsersAll();
		return ResponseEntity.ok().body(list);
	}
	
	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
		this.usersService.deleteUser(id);
		return ResponseEntity.ok().body("User deleted successfully");	
	}
	
	@GetMapping(value = "/users/{id}")
	public ResponseEntity<Users> findById(@PathVariable Long id) {
		var obj = this.usersService.findUser(id);
		return ResponseEntity.ok().body(obj);	
	} 
	
	@PutMapping(value = "/users/{id}")
	public ResponseEntity<String> updateUser(@Valid @RequestBody UsersDTO usersDTO, @PathVariable Long id) {
		String obj = this.usersService.updateUser(usersDTO, id);
		return ResponseEntity.ok().body(obj);		
	}
	
	@PostMapping(value = "/signin")
	public ResponseEntity<?> login(@Valid @RequestBody AuthenticationUserDTO authenticationUserDTO) {
		var userPassword = new UsernamePasswordAuthenticationToken(authenticationUserDTO.login(), authenticationUserDTO.password());
		var authUserPassword = this.authenticationManager.authenticate(userPassword);
		var userToken = this.tokenUserService.generateUserToken((Users) authUserPassword.getPrincipal());
		String cryptPassword = new BCryptPasswordEncoder().encode(authenticationUserDTO.password());
		
		return ResponseEntity.ok(new LoginTokenResponseDTO(authenticationUserDTO.login(), cryptPassword, userToken));
	}
	
	@GetMapping(value = "/me")
	public ResponseEntity<Users> findMe(@RequestParam Long id) {
		var user = this.usersService.findUser(id);
		user.setLastLogin(UserCarUtility.getDateTime());
		return ResponseEntity.ok().body(user);	
	}
	
}
