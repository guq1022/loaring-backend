package com.lams.loaring.config.security.token;

import com.lams.loaring.adventurer.domain.AdventureContext;
import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

	public JwtAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public JwtAuthenticationToken(AdventureContext adventureContext) {
		this(adventureContext.getEmail(),
			adventureContext.getPassword(),
			adventureContext.getAuthorities());
	}

	private JwtAuthenticationToken(Object principal,
		Object credentials,
		Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
}