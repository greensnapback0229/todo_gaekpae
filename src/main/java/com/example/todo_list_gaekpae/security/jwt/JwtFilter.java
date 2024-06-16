package com.example.todo_list_gaekpae.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.todo_list_gaekpae.security.dto.CustomOAuth2User;
import com.example.todo_list_gaekpae.security.dto.UserDto;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

	private final JWTUtil jwtUtil;

	public JwtFilter(JWTUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		if (requestURI.matches("/auth/.*")) {
			filterChain.doFilter(request, response);
			return;
		}

		String authorization = request.getHeader("Authorization");

		if (authorization == null || authorization.isBlank() || authorization.isEmpty() || authorization.equals(
			"undefined")) {
			//System.out.println("[JwtFilter] - token is blank");
			throw new JwtException("INVALID");
		}

		String token = authorization;

		if (jwtUtil.isExpired(token)) {
			throw new JwtException("EXPIRE");
		}

		String uuid = jwtUtil.getUuid(token);
		String role = jwtUtil.getRole(token);

		UserDto userDTO = new UserDto();
		userDTO.setRole(role);
		userDTO.setUuid(uuid);

		CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);

		Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null,
			customOAuth2User.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authToken);
		filterChain.doFilter(request, response);
	}
}
