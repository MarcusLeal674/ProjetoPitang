package com.project.users.cars.usersandheircars.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.project.users.cars.usersandheircars.entities.Users;
import com.project.users.cars.usersandheircars.enums.UsersEnum;
import com.project.users.cars.usersandheircars.objects.Objects;
import com.project.users.cars.usersandheircars.repositories.CarsRepository;
import com.project.users.cars.usersandheircars.repositories.UsersRepository;
import com.project.users.cars.usersandheircars.services.UsersService;
import com.project.users.cars.usersandheircars.utils.UserCarUtility;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class UsersServiceTest {
	private static final Logger log = LoggerFactory.getLogger(CarsServiceTest.class);
	
	private Objects objectTest = new Objects();
	
	@InjectMocks
	UsersService usersService;
	
	@Mock
	private UsersRepository usersRepository;
	
	@Mock
	private CarsRepository carsRepository;
	
	@Autowired
	private UsersRepository usersRepositoryInstance;
	
	@DisplayName("Listing all Users")
	@Test
	void findUsersAllTest() {
		log.info("Tests: Starting the findUsersAllTest Method");
		Mockito.when(usersRepository.findAll()).thenReturn(objectTest.objectListUsers());
		List<Users> listUsersReturn = this.usersService.findUsersAll();
		
		Assertions.assertNotNull(listUsersReturn);		
	}
	
	@DisplayName("Registering user and car false")
	@Test
	void saveUserTest() {
		log.info("Tests: Starting the saveCarTest Method");
		Mockito.when(usersRepository.save(any())).thenReturn(objectTest.objectUsers());
		String msgReturn = this.usersService.saveUserCars(objectTest.objectUsersDTO());
		
		assertEquals("User successfully registered", msgReturn);
	}
	
	@DisplayName("Registering user email true")
	@Test
	void saveUserEmailAlreadyExistsTest() {
		log.info("Tests: Starting the saveUserEmailAlreadyExistsTest Method");
		Mockito.when(usersRepository.existsByEmail(anyString())).thenReturn(true);
		String msgReturn = this.usersService.saveUserCars(objectTest.objectUsersDTO());
		
		assertEquals("Email already exists", msgReturn);
	}
	
	@DisplayName("Registering user Login true")
	@Test
	void saveUserLoginAlreadyExistsTest() {
		log.info("Tests: Starting the saveUserLoginAlreadyExistsTest Method");
		Mockito.when(usersRepository.existsByLogin(anyString())).thenReturn(true);
		String msgReturn = this.usersService.saveUserCars(objectTest.objectUsersDTO());
		
		assertEquals("Login already exists", msgReturn);
	}
	
	@DisplayName("Delete the user by its ID")
	@Test
	void deleteUserByIdTest() {
		log.info("Tests: Starting the deleteUserByIdTest Method");
		usersRepositoryInstance.save(objectTest.objectUsers());
		usersRepositoryInstance.deleteById(objectTest.objectUsers().getIdUsers());
		Optional<Users> result = usersRepositoryInstance.findById(objectTest.objectUsers().getIdUsers());
		
		assertTrue(result.isEmpty());
	}
	
	@DisplayName("Update user information")
	@Test
	void updateUserTest() {
		log.info("Tests: Starting the updateUserTest Method");
		var user = this.updateUserObject();
		usersRepositoryInstance.save(user);
		user.setEmail("userUpdate@com.br");
		usersRepositoryInstance.save(user);
		Optional<Users> result = usersRepositoryInstance.findById(user.getIdUsers());
		
		assertEquals("userUpdate@com.br", result.get().getEmail());
	}
	
	@DisplayName("Search for the user by its ID")
	@Test
	void findByIdTest() {
		log.info("Tests: Starting the findByIdTest Method");
		usersRepositoryInstance.save(objectTest.objectUsers());
		Optional<Users> result = usersRepositoryInstance.findById(objectTest.objectUsers().getIdUsers());
		
		assertEquals(UsersEnum.ADMIN, result.get().getRole());
	}

	private Users updateUserObject() {
		var users = new Users();
		users.setBirthday(new Date());
		users.setCars(this.objectTest.listCars());
		users.setCreatedAt(UserCarUtility.getDateTime());
		users.setEmail("user@com.br");
		users.setFirstName("User");
		users.setIdUsers(1L);
		users.setLastLogin("user.teste");
		users.setLastName("userLast");
		users.setLogin("userLogin");
		users.setPassword("258369");
		users.setPhone("11987542536");
		users.setRole(UsersEnum.USER);
		return users;
	}

}
