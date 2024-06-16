package com.example.todo_list_gaekpae.domain.enums;

import lombok.Getter;

@Getter
public enum Role {
	ROLE_USER("ROLE_USER"),
	ROLE_SOCIAL("ROLE_SOCIAL");

	private String role;

	Role(String role) {
		this.role = role;
	}

	public String value() {
		return role;
	}
}
