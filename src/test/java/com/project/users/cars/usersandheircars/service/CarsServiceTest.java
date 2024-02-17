package com.project.users.cars.usersandheircars.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

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

import com.project.users.cars.usersandheircars.entities.Cars;
import com.project.users.cars.usersandheircars.objects.Objects;
import com.project.users.cars.usersandheircars.repositories.CarsRepository;
import com.project.users.cars.usersandheircars.services.CarsService;

@ExtendWith(MockitoExtension.class)
class CarsServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(CarsServiceTest.class);
	
	private Objects objectTest = new Objects();
	
	@InjectMocks
	CarsService carsService;
	
	@Mock
	CarsRepository carsRepository;
	
	@DisplayName("Listing all cars")
	@Test
	void findCarsAllTest() {
		log.info("Tests: Starting the findCarsAllTest Method");
		
		Mockito.when(carsRepository.findAll()).thenReturn(objectTest.listCars());
		
		List<Cars> listCarsReturn = this.carsService.findCarsAll();
		
		Assertions.assertNotNull(listCarsReturn);		
	}
	
	@DisplayName("Registering user and car false")
	@Test
	void saveCarSuccessfullyRegisteredTest() {
		log.info("Tests: Starting the saveCarSuccessfullyRegisteredTest Method");
		
		Mockito.when(carsRepository.save(any())).thenReturn(objectTest.objectCars());
		
		String msgReturn = this.carsService.saveCar(objectTest.objectCarsDTO());
		
		assertEquals("Car successfully registered", msgReturn);
	}
	
	@DisplayName("Registering user and car true")
	@Test
	void saveCarLicensePlateAlreadyExistsTest() {
		log.info("Tests: Starting the saveCarLicensePlateAlreadyExistsTest Method");
		
		Mockito.when(carsRepository.existsByLicensePlate(anyString())).thenReturn(true);
		
		String msgReturn = this.carsService.saveCar(objectTest.objectCarsDTO());
		
		assertEquals("License plate already exists", msgReturn);
	}
	
}
