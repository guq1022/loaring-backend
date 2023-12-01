package com.lams.loaring.config.security.handler;

import com.lams.loaring.adventurer.domain.Email;
import com.lams.loaring.config.dto.BaseResponseUtils;
import com.lams.loaring.config.security.service.AdventurerService;
import com.lams.loaring.config.security.token.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
public class JwtAuthenticationTokenSuccess implements AuthenticationSuccessHandler {


	private final BaseResponseUtils baseResponseUtils;
	private final JwtTokenUtils jwtTokenUtils;
	private final AdventurerService adventurerService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) {

		var email = (Email) authentication.getPrincipal();
		processResponse(response, email.getEmail());
	}

	private void processResponse(HttpServletResponse response, String principal) {
		String refreshToken = jwtTokenUtils.createRefreshToken(principal);
		jwtTokenUtils.setHeaderAccessToken(response, jwtTokenUtils.createAccessToken(principal));
		jwtTokenUtils.setHeaderRefreshToken(response, jwtTokenUtils.createRefreshToken(principal));
		adventurerService.saveToken(principal, refreshToken);
		baseResponseUtils.sendResponse(response, HttpStatus.OK);
	}
}
