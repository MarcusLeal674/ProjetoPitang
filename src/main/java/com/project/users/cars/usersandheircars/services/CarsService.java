package com.project.users.cars.usersandheircars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.users.cars.usersandheircars.dto.CarsDTO;
import com.project.users.cars.usersandheircars.entities.Cars;
import com.project.users.cars.usersandheircars.repositories.CarsRepository;

@Service
public class CarsService {

	@Autowired
	private CarsRepository carsRepository;

	public List<Cars> findCarsAll() {
		return this.carsRepository.findAll();
	}
	
	public String saveCar(CarsDTO carsDTO) {
		if (this.validadeLicensePlate(carsDTO.getLicensePlate())) {
			return "License plate already exists";
		}
		var car = this.setCar(carsDTO);
		this.carsRepository.save(car);
		return "Car successfully registered";
	}
	
	public Cars findCar(Long id) {
		return this.carsRepository.findById(id).get();
	}
	
	public void deleteCar(Long id) {
		this.carsRepository.deleteById(id);
	}
	
	public String updateCar(CarsDTO carsDTO, Long id) {
		
		if (this.validadeLicensePlate(carsDTO.getLicensePlate())) {
			return "License plate already exists";
		}
		Cars car = this.findCar(id);
		car.setAno(carsDTO.getYear());
		car.setColor(carsDTO.getColor());
		car.setModel(carsDTO.getModel());
		car.setLicensePlate(carsDTO.getLicensePlate());
		this.carsRepository.save(car);
		
		return "Car updated successfully";
	}
		
	private Cars setCar(CarsDTO carsDTO) {
		var car = new Cars();
		car.setAno(carsDTO.getYear());
		car.setColor(carsDTO.getColor());
		car.setLicensePlate(carsDTO.getLicensePlate());
		car.setModel(carsDTO.getModel());

		return car;
	}
	
	private boolean validadeLicensePlate(String licensePlate) {
		return this.carsRepository.existsByLicensePlate(licensePlate);
	}

}
