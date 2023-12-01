package com.lams.loaring.config.security.service;

import com.lams.loaring.adventurer.domain.AdventureContext;
import com.lams.loaring.adventurer.dto.LoginRequest;
import com.lams.loaring.config.security.token.dto.JwtToken;

public interface AdventurerService {

	AdventureContext findByAdventurer(String enterEmail);

	/**
	 * 토큰 발행
	 */
	JwtToken login(LoginRequest loginRequest);

	/**
	 * 토큰 재발행
	 */

	JwtToken reissue(String refreshToken, String email);

	/**
	 * 로그아웃
	 */
	void logout(String accessToken);

	AdventureContext authenticate(LoginRequest loginRequest);

	void verifyPassword(String enterPassword, String password);

	void saveToken(String enterEmail, String refreshToken);
}
