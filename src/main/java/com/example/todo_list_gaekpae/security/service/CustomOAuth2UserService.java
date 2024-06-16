package com.example.todo_list_gaekpae.security.service;

import java.util.UUID;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.todo_list_gaekpae.domain.entity.UserInfo;
import com.example.todo_list_gaekpae.domain.enums.Role;
import com.example.todo_list_gaekpae.domain.repository.UserInfoRepository;
import com.example.todo_list_gaekpae.security.dto.CustomOAuth2User;
import com.example.todo_list_gaekpae.security.dto.KakaoResponse;
import com.example.todo_list_gaekpae.security.dto.OAuth2Response;
import com.example.todo_list_gaekpae.security.dto.UserDto;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	private final UserInfoRepository userInfoRepository;

	public CustomOAuth2UserService(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		//System.out.println(oAuth2User.getName());

		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		OAuth2Response oAuth2Response = null;

		if (registrationId.equals("kakao")) {
			oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
		} else {
			return null;
		}

		String socialId = oAuth2Response.getProvider() + "_" + oAuth2Response.getProviderId();

		UserInfo existData = userInfoRepository.findUserInfoBySocialId(socialId);
		UserDto userDto;
		if (existData == null) {
			String uuid = UUID.randomUUID().toString();
			UserInfo userInfo = UserInfo.builder()
				.role(Role.ROLE_SOCIAL)
				.socialId(socialId)
				.uuid(uuid)
				.build();

			userInfoRepository.save(userInfo);

			userDto = new UserDto().builder()
				.username(socialId)
				.role(Role.ROLE_SOCIAL.getRole())
				.uuid(uuid)
				.build();

		} else {
			existData.updateSocialId(socialId);
			userInfoRepository.save(existData);

			userDto = new UserDto().builder()
				.username(socialId)
				.role(existData.getRole().getRole())
				.uuid(existData.getUuid())
				.build();
		}

		System.out.println(userDto.getRole());
		return new CustomOAuth2User(userDto);
	}
}
