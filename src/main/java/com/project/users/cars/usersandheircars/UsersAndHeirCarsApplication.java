package com.project.users.cars.usersandheircars;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.users.cars.usersandheircars.entities.Cars;
import com.project.users.cars.usersandheircars.entities.Users;
import com.project.users.cars.usersandheircars.enums.UsersEnum;
import com.project.users.cars.usersandheircars.repositories.CarsRepository;
import com.project.users.cars.usersandheircars.repositories.UsersRepository;
import com.project.users.cars.usersandheircars.utils.UserCarUtility;

@SpringBootApplication
public class UsersAndHeirCarsApplication  implements CommandLineRunner {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private CarsRepository carsRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(UsersAndHeirCarsApplication.class, args);
	}

	/**
	 * Criando Usuario e Senha Automantica na base de dados H2
	 */
	@Override
	public void run(String... args) throws Exception {
		List<Cars> listCars = new ArrayList<>();
		Users users = new Users();
		users.setBirthday(new Date());
		users.setEmail("teste@com.br");
		users.setFirstName("Teste");
		users.setLastName("TesteLast");
		users.setLogin("admin");
		users.setPassword(new BCryptPasswordEncoder().encode("123456"));
		users.setPhone("987623456");
		users.setRole(UsersEnum.ADMIN);
		users.setCreatedAt(UserCarUtility.getDateTime());
		
		Cars cars = new Cars();
		cars.setAno(2021);
		cars.setColor("Prata");
		cars.setLicensePlate("LAB-4589");
		cars.setModel("Honda Civic");
		cars.setUsers(users);
		
		listCars.add(cars);
		users.setCars(listCars);
		
		this.usersRepository.save(users);
		this.carsRepository.save(cars);
		
	}

}
