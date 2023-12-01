package com.lams.loaring.config.security.password;

import com.lams.loaring.adventurer.domain.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class BCryptPasswordEncoderAdapter extends BCryptPasswordEncoder implements
	PasswordEncoderHelper {

	@Override
	public boolean matches(CharSequence rawPassword, Password encodedPassword) {
		return super.matches(rawPassword, encodedPassword.getPassword());
	}

}
