package com.project.users.cars.usersandheircars.dto;

import java.util.Date;
import java.util.List;

import com.project.users.cars.usersandheircars.enums.UsersEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsersDTO {	
	
	@NotBlank(message = "Missing fields")
	private String firstName;
	
	@NotBlank(message = "Missing fields")
	private String lastName;
	
	@NotBlank(message = "Missing fields")
	@Email
	private String email;
	
	private Date birthday;
	
	@NotBlank(message = "Missing fields")
	private String login;
	
	@NotBlank(message = "Missing fields")
	private String password;
	
	@NotBlank(message = "Missing fields")
	private String phone;
	
	private List<CarsDTO> cars;	
	
	private UsersEnum role;
}
