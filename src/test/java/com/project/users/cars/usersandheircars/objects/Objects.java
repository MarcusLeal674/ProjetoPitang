package com.project.users.cars.usersandheircars.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.users.cars.usersandheircars.dto.AuthenticationUserDTO;
import com.project.users.cars.usersandheircars.dto.CarsDTO;
import com.project.users.cars.usersandheircars.dto.UsersDTO;
import com.project.users.cars.usersandheircars.entities.Cars;
import com.project.users.cars.usersandheircars.entities.Users;
import com.project.users.cars.usersandheircars.enums.UsersEnum;
import com.project.users.cars.usersandheircars.utils.UserCarUtility;

public class Objects {
	
	public CarsDTO objectCarsDTO() {
		var carDTO = new CarsDTO();
		carDTO.setColor("Branco");
		carDTO.setIdCars(1L);
		carDTO.setLicensePlate("ELO-2376");
		carDTO.setModel("Gol");
		carDTO.setYear(2020);
		return carDTO;
	}
	
	public Cars objectCars() {
		var car = new Cars();
		car.setColor("Branco");
		car.setIdCars(1L);
		car.setLicensePlate("ELO-2376");
		car.setModel("Gol");
		car.setAno(2020);
		return car;
	}
	
	public List<Cars> listCars() {
		List<Cars> listCars = new ArrayList<>();
		listCars.add(this.objectCars());
		return listCars;
	}
	
	public List<CarsDTO> listCarsDTO() {
		List<CarsDTO> listCarsDTO = new ArrayList<>();
		listCarsDTO.add(this.objectCarsDTO());
		return listCarsDTO;
	}
	
	public Users objectUsers() {
		var users = new Users();
		users.setBirthday(new Date());
		users.setCars(this.listCars());
		users.setCreatedAt(UserCarUtility.getDateTime());
		users.setEmail("teste@com.br");
		users.setFirstName("Teste");
		users.setIdUsers(1L);
		users.setLastLogin("teste.teste");
		users.setLastName("tese");
		users.setLogin("test");
		users.setPassword("123456");
		users.setPhone("8196969699");
		users.setRole(UsersEnum.ADMIN);
		return users;
	}
	
	public UsersDTO objectUsersDTO() {
		var usersDTO = new UsersDTO();
		usersDTO.setBirthday(new Date());
		usersDTO.setCars(this.listCarsDTO());
		usersDTO.setEmail("testeDTO@com.br");
		usersDTO.setFirstName("TesteDTO");
		usersDTO.setIdUsers(1L);
		usersDTO.setLastName("teseDTO");
		usersDTO.setLogin("testDTO");
		usersDTO.setPassword("7890");
		usersDTO.setPhone("1198457632");
		usersDTO.setRole(UsersEnum.USER);
		return usersDTO;
	}
	
	public List<Users> objectListUsers() {
		List<Users> listUsers = new ArrayList<>();
		listUsers.add(this.objectUsers());
		return listUsers;
	}
	
	public AuthenticationUserDTO objectAuthenticationUserDTO() {
		var authenticationUserDTO = new AuthenticationUserDTO("ADMIN", "123456");
		return authenticationUserDTO;
	}

}
