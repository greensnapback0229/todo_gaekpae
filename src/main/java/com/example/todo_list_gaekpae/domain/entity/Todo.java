package com.example.todo_list_gaekpae.domain.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "todo_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_info_id")
	private UserInfo userInfo;

	private String title;

	private Boolean done;

	private LocalDate endDate;

	@Builder
	Todo(String title, Boolean done, LocalDate endDate, UserInfo userInfo){
		this.title = title;
		this.done = done;
		this.endDate = endDate;
		this.userInfo = userInfo;
	}

	public void updateDone(Boolean done){
		this.done = done;
	}
}
