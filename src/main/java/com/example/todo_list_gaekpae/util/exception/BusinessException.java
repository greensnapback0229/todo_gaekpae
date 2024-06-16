package com.example.todo_list_gaekpae.util.exception;


import com.example.todo_list_gaekpae.domain.enums.ResponseCode;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
	private ResponseCode responseCode;

	public BusinessException() {
		super();
	}

	public BusinessException(ResponseCode responseCode) {
		super(responseCode.getMessage());
		this.responseCode = responseCode;
	}
}
