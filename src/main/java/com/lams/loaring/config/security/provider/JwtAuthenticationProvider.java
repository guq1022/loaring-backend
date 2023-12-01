package com.lams.loaring.config.security.provider;

import com.lams.loaring.config.security.service.AdventurerService;
import com.lams.loaring.config.security.token.JwtAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private final AdventurerService adventurerService;

	@Override
	public Authentication authenticate(Authentication authentication)
		throws AuthenticationException {

		final var enterEmail = getEmail(authentication);
		final var enterPassword = getPassword(authentication);

		final var adventureContext = adventurerService.findByAdventurer(enterEmail);

		adventurerService.verifyPassword(enterPassword, adventureContext.getPassword());
		return new JwtAuthenticationToken(adventureContext);
	}

	private String getEmail(Authentication authentication) {
		return (String) authentication.getPrincipal();
	}

	private String getPassword(Authentication authentication) {
		return String.valueOf(authentication.getCredentials());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(JwtAuthenticationToken.class);
	}
}