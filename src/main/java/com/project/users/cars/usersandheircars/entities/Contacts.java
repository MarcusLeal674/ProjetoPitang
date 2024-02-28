package com.project.users.cars.usersandheircars.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Contacts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", columnDefinition="VARCHAR(100)")
	private String name;
	
	@Column(name = "surname", columnDefinition="VARCHAR(100)")
	private String surname;
	
	@Column(name = "email", columnDefinition="VARCHAR(255)")
	private String email;
	
	@Column(name = "cell_phone", columnDefinition="VARCHAR(11)")
	private String cellPhone;
	
	@Column(name = "favorite")
	private Boolean favorite;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "contato_dh_cad")
    private Date contatoDhCad;
}
