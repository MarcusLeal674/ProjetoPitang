package com.project.users.cars.usersandheircars.enums;

public enum UsersEnum {
	
	ADMIN("admin"),
	USER("user");
	
	private String role;
	
	UsersEnum(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	
}
