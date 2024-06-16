package com.example.todo_list_gaekpae.service;

import java.sql.PseudoColumnUsage;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.todo_list_gaekpae.controller.dto.request.LoginReq;
import com.example.todo_list_gaekpae.controller.dto.request.RegisterReq;
import com.example.todo_list_gaekpae.controller.dto.request.SignUpReq;
import com.example.todo_list_gaekpae.controller.dto.response.SignUpRes;
import com.example.todo_list_gaekpae.domain.Response;
import com.example.todo_list_gaekpae.domain.entity.UserInfo;
import com.example.todo_list_gaekpae.domain.enums.ResponseCode;
import com.example.todo_list_gaekpae.domain.enums.Role;
import com.example.todo_list_gaekpae.domain.repository.UserInfoRepository;
import com.example.todo_list_gaekpae.security.SecurityUtil;
import com.example.todo_list_gaekpae.security.jwt.JWTUtil;
import com.example.todo_list_gaekpae.util.exception.BusinessException;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	private final UserInfoRepository userInfoRepository;

	private final JWTUtil jwtUtil;

	private final SecurityUtil securityUtil;


	UserService(UserInfoRepository userInfoRepository, JWTUtil jwtUtil, SecurityUtil securityUtil){
		this.userInfoRepository = userInfoRepository;
		this.jwtUtil = jwtUtil;
		this.securityUtil = securityUtil;
	}

	@Transactional
	public Response<SignUpRes> signUp(SignUpReq req){
		String uuid = UUID.randomUUID().toString();

		UserInfo userInfo = UserInfo.builder()
			.email(req.getEmail())
			.password(req.getPassword())
			.username(req.getUsername())
			.uuid(uuid)
			.role(Role.ROLE_USER)
			.build();

		userInfoRepository.save(userInfo);

		String token = jwtUtil.createJwt(uuid, Role.ROLE_USER.getRole(), 60 * 600 * 600L);

		return Response.ok(new SignUpRes(token));
	}

	public Response<SignUpRes> login(LoginReq req){
		String email = req.getEmail();
		String password = req.getPassword();
		String token;
		UserInfo userInfo = userInfoRepository.findUserInfoByEmail(email)
			.orElseThrow(() ->	new BusinessException(ResponseCode.USER_NOT_FOUND));

		if(userInfo.getPassword().equals(password)){
			token = jwtUtil.createJwt(userInfo.getUuid(), Role.ROLE_USER.getRole(), 60 * 600 * 600L);
		}
		else{
			throw new BusinessException(ResponseCode.PASSWORD_IS_NOT_MATCH);
		}

		return Response.ok(new SignUpRes(token));
	}

	@Transactional
	public Response<SignUpRes> register(RegisterReq req){
		UserInfo userInfo = securityUtil.getUserInfo();
		userInfo.addUsername(req.getUsername());

		String token = jwtUtil.createJwt(userInfo.getUuid(), Role.ROLE_USER.getRole(), 60 * 600 * 600L);
		return Response.ok(new SignUpRes(token));
	}
}
