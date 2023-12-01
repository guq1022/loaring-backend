package com.lams.loaring.config.security.service;

import com.lams.loaring.config.security.token.domain.RefreshToken;

public interface RefreshService {

	RefreshToken findToken(String email);

	RefreshToken save(String email, String refreshToken);

	void deleteToken(String email);

}
