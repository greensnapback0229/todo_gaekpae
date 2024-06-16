package com.example.todo_list_gaekpae.domain.enums;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ResponseCode {

	SUCCESS(200, "GEN-000", HttpStatus.OK, "Success"),

	//Todo : todo
	TODO_ID_NOT_AVAILABLE(404, "TODO-000", HttpStatus.NOT_FOUND, "post id is not available"),
	TODO_IS_EMPTY(200, "TODO-001", HttpStatus.OK, "todo is empty"),
	//Security error : SEC
	TOKEN_EXPIRED(400, "SEC-001", HttpStatus.BAD_REQUEST, "token is expired or not available"),
	TOKEN_NOT_AVAILABLE(400, "SEC-002", HttpStatus.BAD_REQUEST, "token is not available "),
	USER_NOT_FOUND(404, "SEC-003", HttpStatus.NOT_FOUND, "user is not found"),
	PASSWORD_IS_NOT_MATCH(400, "SEC-004", HttpStatus.BAD_REQUEST, "password is not match ");

	private final Integer status;
	private final String code;
	private final HttpStatus httpStatus;
	private final String message;

	ResponseCode(Integer status, String code, HttpStatus httpStatus, String message) {
		this.status = status;
		this.code = code;
		this.httpStatus = httpStatus;
		this.message = message;
	}

}
