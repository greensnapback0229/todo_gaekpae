package com.example.todo_list_gaekpae.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.todo_list_gaekpae.domain.Response;

@Controller
@ResponseBody
public class SecurityController {
	@GetMapping("/token/check")
	public Response tokenCheck() {
		return Response.ok();
	}
}
