package com.project.users.cars.usersandheircars.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

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

import com.project.users.cars.usersandheircars.entities.Cars;
import com.project.users.cars.usersandheircars.objects.Objects;
import com.project.users.cars.usersandheircars.repositories.CarsRepository;
import com.project.users.cars.usersandheircars.services.CarsService;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class CarsServiceTest {	
	private static final Logger log = LoggerFactory.getLogger(CarsServiceTest.class);
	
	private Objects objectTest = new Objects();
	
	@InjectMocks
	CarsService carsService;
	
	@Mock
	CarsRepository carsRepository;
	
	@Autowired
	CarsRepository carsRepositoryInstance;
	
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
	void saveCarTest() {
		log.info("Tests: Starting the saveCarTest Method");
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
	
	@DisplayName("Search for the car by its ID")
	@Test
	void findByIdTest() {
		log.info("Tests: Starting the findByIdTest Method");
		carsRepositoryInstance.save(objectTest.objectCars());
		Optional<Cars> result = carsRepositoryInstance.findById(objectTest.objectCars().getIdCars());
		
		assertEquals(2020, result.get().getAno());
	}
	
	@DisplayName("Delete the car by its ID")
	@Test
	void deleteCarByIdTest() {
		log.info("Tests: Starting the deleteCarByIdTest Method");
		carsRepositoryInstance.save(objectTest.objectCars());
		carsRepositoryInstance.deleteById(objectTest.objectCars().getIdCars());
		Optional<Cars> result = carsRepositoryInstance.findById(objectTest.objectCars().getIdCars());
		
		assertTrue(result.isEmpty());
	}
	
	@DisplayName("Update car information")
	@Test
	void updateCarTest() {
		log.info("Tests: Starting the updateCarTest Method");
		var car = this.updateCarObject();
		carsRepositoryInstance.save(car);
		car.setAno(2024);
		carsRepositoryInstance.save(car);
		Optional<Cars> result = carsRepositoryInstance.findById(car.getIdCars());
		
		assertEquals(2024, result.get().getAno());
	}

	private Cars updateCarObject() {
		var car = new Cars();
		car.setColor("Preto");
		car.setIdCars(2L);
		car.setLicensePlate("CAR-9032");
		car.setModel("Civic");
		car.setAno(2021);
		return car;
	}
	
}