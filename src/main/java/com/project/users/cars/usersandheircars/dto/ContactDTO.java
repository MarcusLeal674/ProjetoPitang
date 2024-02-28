package com.project.users.cars.usersandheircars.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ContactDTO {
	
	@JsonProperty("_id")
	private Long id;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String cellPhone;
	
	private Boolean favorite;
	
	private Date contatoDhCad;

}
