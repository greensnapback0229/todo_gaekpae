package com.example.todo_list_gaekpae.util.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.todo_list_gaekpae.domain.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	public Response handleBusinessException(BusinessException ex) {
		return Response.errorResponse(ex.getResponseCode());
	}
}
