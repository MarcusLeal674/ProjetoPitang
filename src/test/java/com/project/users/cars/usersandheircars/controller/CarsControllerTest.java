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

import com.project.users.cars.usersandheircars.entities.Cars;
import com.project.users.cars.usersandheircars.objects.Objects;
import com.project.users.cars.usersandheircars.services.CarsService;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class CarsControllerTest {
	private static final Logger log = LoggerFactory.getLogger(CarsControllerTest.class);
	
	private Objects objectTest = new Objects();
	
	@InjectMocks
	CarsController carsController;
	
	@Mock
	CarsService carsService;
	
	@DisplayName("Registering car")
	@Test
	void saveCarTest() {
		log.info("Tests: Starting the saveCarTest Method");		
		Mockito.when(carsService.saveCar(any())).thenReturn(anyString());
		ResponseEntity<String> responseEntityReturn = this.carsController.saveUserCar(objectTest.objectCarsDTO());
		
		Assertions.assertEquals(HttpStatus.OK.value(), responseEntityReturn.getStatusCode().value());		
	}
	
	@DisplayName("Listing all cars")
	@Test
	void findCarsAllTest() {
		log.info("Tests: Starting the findCarsAllTest Method");
		Mockito.when(carsService.findCarsAll()).thenReturn(objectTest.listCars());
		ResponseEntity<List<Cars>> responseEntityReturn = this.carsController.findCarsAll();
		
		Assertions.assertEquals(HttpStatus.OK.value(), responseEntityReturn.getStatusCode().value());		
	}
	
	@DisplayName("Search for the car by its ID")
	@Test
	void findByIdTest() {
		log.info("Tests: Starting the findByIdTest Method");
		Mockito.when(carsService.findCar(anyLong())).thenReturn(objectTest.objectCars());
		ResponseEntity<Cars> responseEntityReturn = this.carsController.findById(1L);
		
		Assertions.assertEquals(HttpStatus.OK.value(), responseEntityReturn.getStatusCode().value());		
	}
	
	@DisplayName("Delete the car by its ID")
	@Test
	void deleteCarByIdTest() {
		log.info("Tests: Starting the deleteCarByIdTest Method");
		doNothing().when(carsService).deleteCar(1L);
		ResponseEntity<String> responseEntityReturn = this.carsController.deleteCarById(1L);
		
		Assertions.assertEquals(HttpStatus.OK.value(), responseEntityReturn.getStatusCode().value());		
	}
	
	@DisplayName("Update car information")
	@Test
	void updateCarTest() {
		log.info("Tests: Starting the updateCarTest Method");
		Mockito.when(carsService.updateCar(any(), anyLong())).thenReturn("Car updated successfully or License plate already exists");
		ResponseEntity<String> responseEntityReturn = this.carsController.updateCar(objectTest.objectCarsDTO(), 1L);
		
		Assertions.assertEquals(HttpStatus.OK.value(), responseEntityReturn.getStatusCode().value());		
	}
}
