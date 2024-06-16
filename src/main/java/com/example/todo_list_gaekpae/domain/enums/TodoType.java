package com.example.todo_list_gaekpae.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TodoType {

	MAIN("Main"),
	SUB("Sub");

	private String type;

}
