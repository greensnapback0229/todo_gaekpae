package com.example.todo_list_gaekpae.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_list_gaekpae.controller.dto.request.LoginReq;
import com.example.todo_list_gaekpae.controller.dto.request.RegisterReq;
import com.example.todo_list_gaekpae.controller.dto.request.SignUpReq;
import com.example.todo_list_gaekpae.controller.dto.response.SignUpRes;
import com.example.todo_list_gaekpae.domain.Response;
import com.example.todo_list_gaekpae.service.UserService;

@RestController
public class UserController {

	private final UserService userService;

	UserController(UserService userService){
		this.userService = userService;
	}

	@PostMapping("/auth/signup")
	public Response<SignUpRes> signUp(@RequestBody SignUpReq req){
		return userService.signUp(req);
	}

	@PostMapping("/auth/login")
	public Response<SignUpRes> signUp(@RequestBody LoginReq req){
		return userService.login(req);
	}

	@PostMapping("/account/register")
	public Response<SignUpRes> register(@RequestBody RegisterReq req){
		return userService.register(req);
	}

}
