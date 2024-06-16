package com.example.todo_list_gaekpae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_list_gaekpae.controller.dto.request.CreateReq;
import com.example.todo_list_gaekpae.controller.dto.request.DeleteReq;
import com.example.todo_list_gaekpae.controller.dto.request.UpdateReq;
import com.example.todo_list_gaekpae.controller.dto.response.TodoListRes;
import com.example.todo_list_gaekpae.domain.Response;
import com.example.todo_list_gaekpae.service.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {

	private final TodoService todoService;
	TodoController(TodoService todoService){
		this.todoService = todoService;
	}

	@PostMapping("/create")
	public Response createTodo(@RequestBody CreateReq req){
		return todoService.createTodo(req);
	}

	@GetMapping
	public Response<TodoListRes> getTodo(@RequestParam Integer year, Integer month){
		return todoService.getMonthTodo(year, month);
	}

	@PostMapping("/update")
	public Response updateTodo(@RequestBody UpdateReq req){
		return todoService.updateTodo(req);
	}

	@PostMapping("/delete")
	public Response updateTodo(@RequestBody DeleteReq req){
		return todoService.deleteTodo(req);
	}
}
