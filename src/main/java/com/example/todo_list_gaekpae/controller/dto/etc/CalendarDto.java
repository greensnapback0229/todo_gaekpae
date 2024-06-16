package com.example.todo_list_gaekpae.controller.dto.etc;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CalendarDto {
	private Long date;
	private Long mainCount;
	private Long subCount;
}
