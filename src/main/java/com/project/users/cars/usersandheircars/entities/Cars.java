package com.project.users.cars.usersandheircars.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Cars {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idCars;
	
	@Column(name = "ano")
	private int ano;
	
	@Column(name = "license_plate")
	private String licensePlate;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "color")
	private String color;
	 
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "users_id", referencedColumnName = "id")
	private Users users;
}
