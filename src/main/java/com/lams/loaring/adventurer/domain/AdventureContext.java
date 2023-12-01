package com.lams.loaring.adventurer.domain;

import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class AdventureContext implements UserDetails {

	private Adventurer adventurer;

	public AdventureContext(@NotNull Adventurer adventurer) {
		this.adventurer = adventurer;
	}

	@Override
	public String getUsername() {
		return adventurer.getUserName();
	}

	@Override
	public String getPassword() {
		return adventurer.getUserPassword();
	}

	public Email getEmail() {
		return adventurer.getEmail();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return adventurer.getAdventureAuthority();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
