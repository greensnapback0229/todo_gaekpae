package com.example.todo_list_gaekpae.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.todo_list_gaekpae.domain.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	Optional<UserInfo> findUserInfoByUuid(@Param("uuid")String uuid);

	UserInfo findUserInfoBySocialId(@Param("social_id")String socialId);

	Optional<UserInfo> findUserInfoByEmail(@Param("email")String email);


}
