package com.example.todo_list_gaekpae.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.todo_list_gaekpae.domain.entity.UserInfo;
import com.example.todo_list_gaekpae.domain.enums.ResponseCode;
import com.example.todo_list_gaekpae.domain.repository.UserInfoRepository;
import com.example.todo_list_gaekpae.security.dto.CustomOAuth2User;
import com.example.todo_list_gaekpae.util.exception.BusinessException;

@Component
public class SecurityUtil {

	UserInfoRepository userInfoRepository;

	SecurityUtil(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}

	public CustomOAuth2User getContextUserInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			return null;
		}

		Object principal = authentication.getPrincipal();

		if (principal instanceof CustomOAuth2User) {
			return (CustomOAuth2User)principal;
		}

		return null;
	}

	public UserInfo getUserInfo() {
		CustomOAuth2User user = getContextUserInfo();

		if (user == null) {
			return null;
		}

		String uuid = user.getUuid();
		System.out.println("[securityUtil] - uuid=" + uuid);
		return userInfoRepository.findUserInfoByUuid(uuid)
			.orElseThrow( () ->	new BusinessException(ResponseCode.USER_NOT_FOUND));
	}
}
