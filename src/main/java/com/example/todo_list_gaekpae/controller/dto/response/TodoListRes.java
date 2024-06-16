package com.example.todo_list_gaekpae.controller.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.example.todo_list_gaekpae.controller.dto.etc.TodoDto;
import com.example.todo_list_gaekpae.domain.entity.Todo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TodoListRes {
	List<TodoDto> todoList;


	public static TodoListRes fromEntityList(List<Todo> todoList){
		List<TodoDto> result = new ArrayList<>();
		for(Todo todo : todoList){
			TodoDto dto = TodoDto.builder()
				.todoId(todo.getId())
				.title(todo.getTitle())
				.done(todo.getDone())
				.endDate(todo.getEndDate())
				.build();

			result.add(dto);
		}

		return new TodoListRes(result);
	}
}
