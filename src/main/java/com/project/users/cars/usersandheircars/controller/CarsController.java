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

import com.project.users.cars.usersandheircars.dto.CarsDTO;
import com.project.users.cars.usersandheircars.entities.Cars;
import com.project.users.cars.usersandheircars.services.CarsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class CarsController {
	
	@Autowired
	private CarsService carsService;
	
	@PostMapping(value = "/cars")
	public ResponseEntity<String> saveUserCar(@Valid @RequestBody CarsDTO carsDTO) {
		String obj = this.carsService.saveCar(carsDTO);
		return ResponseEntity.ok().body(obj);		
	}
	
	@GetMapping(value = "/cars")
	public ResponseEntity<List<Cars>> findCarsAll() {
		List<Cars> list = this.carsService.findCarsAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/cars/{id}")
	public ResponseEntity<Cars> findById(@PathVariable Long id) {
		var obj = this.carsService.findCar(id);
		return ResponseEntity.ok().body(obj);	
	}
	
	@DeleteMapping(value = "/cars/{id}")
	public ResponseEntity<String> deleteCarById(@PathVariable Long id) {
		this.carsService.deleteCar(id);
		return ResponseEntity.ok().body("Carro deleted successfully");	
	}
	
	@PutMapping(value = "/cars/{id}")
	public ResponseEntity<String> updateCar(@Valid @RequestBody CarsDTO carsDTO, @PathVariable Long id) {
		String obj = this.carsService.updateCar(carsDTO, id);
		return ResponseEntity.ok().body(obj);		
	}

}
