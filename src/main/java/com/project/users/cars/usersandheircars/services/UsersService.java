package com.project.users.cars.usersandheircars.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.users.cars.usersandheircars.dto.CarsDTO;
import com.project.users.cars.usersandheircars.dto.UsersDTO;
import com.project.users.cars.usersandheircars.entities.Cars;
import com.project.users.cars.usersandheircars.entities.Users;
import com.project.users.cars.usersandheircars.repositories.CarsRepository;
import com.project.users.cars.usersandheircars.repositories.UsersRepository;
import com.project.users.cars.usersandheircars.utils.UserCarUtility;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private CarsRepository carsRepository;
	
	public List<Users> findUsersAll() {
		return this.usersRepository.findAll();
	}
	
	public String saveUserCars(UsersDTO usersDTO) {
		
		if(this.validateEmail(usersDTO.getEmail())) {
			return "Email already exists";
		} else if (this.validateLogin(usersDTO.getLogin())) {
			return "Login already exists";
		} else {
			var user = this.setUser(usersDTO);	
			this.saveUser(usersDTO, user);	
			this.saveCars(usersDTO, user);			
			return "User successfully registered";			
		}
	}
	
	public void deleteUser(Long id) {
		this.usersRepository.deleteById(id);
	}
	
	public Users findUser(Long id) {
		return this.usersRepository.findById(id).get();
	}
	
	public String updateUser(UsersDTO usersDTO, Long id) {
		
		if(this.validateEmail(usersDTO.getEmail())) {
			return "Email already exists";
		} else if (this.validateLogin(usersDTO.getLogin())) {
			return "Login already exists";
		} else {
			var users = this.findUser(id);
			String cryptPassword = this.cryptedPassword(usersDTO.getPassword());
			
			users.setBirthday(usersDTO.getBirthday());
			users.setEmail(usersDTO.getEmail());
			users.setFirstName(usersDTO.getFirstName());
			users.setLastName(usersDTO.getLastName());
			users.setLogin(usersDTO.getLogin());
			users.setPassword(cryptPassword);
			users.setPhone(usersDTO.getPhone());
			users.setRole(usersDTO.getRole());
			usersRepository.save(users);		
			
			return "User successfully update";			
		}			
		
	}

	private void saveUser(UsersDTO usersDTO, Users user) {
		List<Cars> listCars = addCars(usersDTO);
		String cryptPassword = this.cryptedPassword(user.getPassword());
		user.setPassword(cryptPassword);
		user.setCars(listCars);
		this.usersRepository.save(user);
	}
	
	private String cryptedPassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
	
	private void saveCars(UsersDTO usersDTO, Users user) {		
		for (CarsDTO carsDTO : usersDTO.getCars()) {
			Cars car = new Cars();
			car.setColor(carsDTO.getColor());
			car.setLicensePlate(carsDTO.getLicensePlate());
			car.setModel(carsDTO.getModel());
			car.setAno(carsDTO.getYear());
			car.setUsers(user);
			this.carsRepository.save(car);
		}
	}

	private Users setUser(UsersDTO usersDTO) {
		Users user = new Users();		
		user.setBirthday(usersDTO.getBirthday());		
		user.setEmail(usersDTO.getEmail());
		user.setFirstName(usersDTO.getFirstName());
		user.setLastName(usersDTO.getLastName());
		user.setLogin(usersDTO.getLogin());
		user.setPassword(usersDTO.getPassword());
		user.setPhone(usersDTO.getPhone());
		user.setRole(usersDTO.getRole());
		user.setCreatedAt(UserCarUtility.getDateTime());
		return user;
	}
	

	private List<Cars> addCars(UsersDTO usersDTO) {		
		List<Cars> listCars = new ArrayList<>();		
		for (CarsDTO carsDTO : usersDTO.getCars()) {
			Cars car = new Cars();
			car.setColor(carsDTO.getColor());
			car.setLicensePlate(carsDTO.getLicensePlate());
			car.setModel(carsDTO.getModel());
			car.setAno(carsDTO.getYear());
			listCars.add(car);
		}
		return listCars;
	}
	
	private boolean validateEmail(String email) {
		return this.usersRepository.existsByEmail(email);
	}
	
	private boolean validateLogin(String login) {
		return this.usersRepository.existsByLogin(login);
	}

}
