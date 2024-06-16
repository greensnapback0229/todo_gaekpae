package com.example.todo_list_gaekpae.security.jwt;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.todo_list_gaekpae.domain.Response;
import com.example.todo_list_gaekpae.domain.enums.ResponseCode;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (JwtException e) {
			setErrorResponse(HttpStatus.OK, response, e);
		}
	}

	public void setErrorResponse(HttpStatus status, HttpServletResponse res, Throwable ex) throws IOException {
		res.setStatus(status.value());
		res.setContentType("application/json; charset=UTF-8");

		if (ex.getMessage().equals("EXPIRE")) {
				String resJson = Response.errorResponse(ResponseCode.TOKEN_EXPIRED).convertToJson();
			res.getWriter().write(resJson);
		} else if (ex.getMessage().equals("INVALID")) {
			String resJson = Response.errorResponse(ResponseCode.TOKEN_EXPIRED).convertToJson();
			res.getWriter().write(resJson);
		}
	}
}
