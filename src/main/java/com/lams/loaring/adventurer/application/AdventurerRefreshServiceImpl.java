package com.lams.loaring.adventurer.application;

import com.lams.loaring.adventurer.infra.RefreshRepository;
import com.lams.loaring.config.exception.BaseEntityNotFoundException;
import com.lams.loaring.config.security.service.RefreshService;
import com.lams.loaring.config.security.token.domain.RefreshToken;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdventurerRefreshServiceImpl implements RefreshService {

	private final RefreshRepository refreshRepository;

	@Override
	public RefreshToken findToken(String userName) {
		return refreshRepository.findById(userName)
			.orElseThrow(() -> new BaseEntityNotFoundException("리프레시 토큰"));

	}

	@Override
	public RefreshToken save(String email, String refreshToken) {
		return refreshRepository.save(RefreshToken.builder()
			.id(email)
			.refreshToken(refreshToken)
			.build());
	}

	@Override
	public void deleteToken(String email) {
		refreshRepository.delete(findToken(email));
	}

}
