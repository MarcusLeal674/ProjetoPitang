package com.project.users.cars.usersandheircars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.users.cars.usersandheircars.entities.Cars;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Long> {
	boolean existsByLicensePlate(String licensePlate);
}
