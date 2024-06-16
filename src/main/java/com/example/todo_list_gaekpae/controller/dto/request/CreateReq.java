package com.example.todo_list_gaekpae.controller.dto.request;

import java.time.LocalDate;

import com.example.todo_list_gaekpae.domain.entity.Todo;
import com.example.todo_list_gaekpae.domain.entity.UserInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateReq {

	private String title;
	private Boolean done;
	private LocalDate endDate;


	public static Todo toEntity(CreateReq req, UserInfo userInfo){
		Todo todoEntity = Todo.builder()
			.title(req.getTitle())
			.done(req.getDone())
			.endDate(req.getEndDate())
			.userInfo(userInfo)
			.build();

		return todoEntity;
	}
}
