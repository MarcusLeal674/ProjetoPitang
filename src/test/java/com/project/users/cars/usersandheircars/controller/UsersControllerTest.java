package com.project.users.cars.usersandheircars.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.users.cars.usersandheircars.entities.Users;
import com.project.users.cars.usersandheircars.objects.Objects;
import com.project.users.cars.usersandheircars.services.UsersService;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class UsersControllerTest {
	private static final Logger log = LoggerFactory.getLogger(UsersControllerTest.class);
	
	private Objects objectTest = new Objects();
	
	@InjectMocks
	UsersController usersController;
	
	@Mock
	UsersService usersService;

	@DisplayName("Registering User and Cars")
	@Test
	void saveUserandCarsTest() {
		log.info("Tests: Starting the saveUserandCarsTest Method");			
		Mockito.when(usersService.saveUserCars(any())).thenReturn(anyString());
		ResponseEntity<String> responseEntityReturn = this.usersController.saveUserCar(objectTest.objectUsersDTO());
		
		Assertions.assertEquals(HttpStatus.OK.value(), responseEntityReturn.getStatusCode().value());		
	}
	
	@DisplayName("Listing all Users and Cars")
	@Test
	void findUsersAllTest() {
		log.info("Tests: Starting the findUsersAllTest Method");	
		Mockito.when(usersService.findUsersAll()).thenReturn(objectTest.objectListUsers());
		ResponseEntity<List<Users>> responseEntityReturn = this.usersController.findUsersAll();
		
		Assertions.assertEquals(HttpStatus.OK.value(), responseEntityReturn.getStatusCode().value());
	}
	
	@DisplayName("Delete the Users and Cars by its ID")
	@Test
	void deleteUsersCarsByIdTest() {
		log.info("Tests: Starting the deleteUsersCarsByIdTest Method");
		doNothing().when(usersService).deleteUser(anyLong());
		ResponseEntity<String> responseEntityReturn = this.usersController.deleteUserById(1L);
		
		Assertions.assertEquals(HttpStatus.OK.value(), responseEntityReturn.getStatusCode().value());		
	}
	
	@DisplayName("Search for the User and Cars by its ID")
	@Test
	void findByIdTest() {
		log.info("Tests: Starting the findByIdTest Method");
		Mockito.when(usersService.findUser(anyLong())).thenReturn(objectTest.objectUsers());
		ResponseEntity<Users> responseEntityReturn = this.usersController.findById(1L);
		
		Assertions.assertEquals(HttpStatus.OK.value(), responseEntityReturn.getStatusCode().value());	
	}
	
	@DisplayName("Update users information")
	@Test
	void updateUsersTest() {
		log.info("Tests: Starting the updateUsersTest Method");
		Mockito.when(usersService.updateUser(any(), anyLong())).thenReturn("Email already exists or Login already exists or User successfully update");
		ResponseEntity<String> responseEntityReturn = this.usersController.updateUser(objectTest.objectUsersDTO(), 1L);
		
		Assertions.assertEquals("Email already exists or Login already exists or User successfully update", responseEntityReturn.getBody());	
		Assertions.assertEquals(HttpStatus.OK.value(), responseEntityReturn.getStatusCode().value());
	}
	
}
