package com.example.todo_list_gaekpae.domain.entity;

import com.example.todo_list_gaekpae.domain.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user_info", uniqueConstraints = {
	@UniqueConstraint(
		name = "uuid_unique",
		columnNames = "uuid"
	)
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo {

	@Id
	@Column(name = "user_info_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true)
	private String username;

	private String email;

	private String password;

	private String socialId;

	private String uuid;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Builder
	UserInfo(String username, String email, String password, String uuid, String socialId, Role role){
		this.username = username;
		this.email = email;
		this.password = password;
		this.socialId = socialId;
		this.uuid = uuid;
		this.role = role;
	}

	public void updateSocialId(String socialId){
		this.socialId = socialId;
	}

	public void addUsername(String username) {
		this.username = username;
	}
}
