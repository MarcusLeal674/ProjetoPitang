package com.project.users.cars.usersandheircars.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CarsDTO {
	
private Long idCars;
	
	private int year;
	
	@NotBlank(message = "Missing fields")
	private String licensePlate;
	
	@NotBlank(message = "Missing fields")
	private String model;
	
	@NotBlank(message = "Missing fields")
	private String color;
}
