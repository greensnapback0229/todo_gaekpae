package com.example.todo_list_gaekpae.controller.dto.etc;

import java.time.LocalDate;

import com.example.todo_list_gaekpae.domain.enums.TodoType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class TodoDto {

	private Long todoId;
	private String title;
	private Boolean done;
	private LocalDate endDate;
}
