package com.lams.loaring.config.security.password;

import com.lams.loaring.adventurer.domain.Password;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface PasswordEncoderHelper extends PasswordEncoder {

	boolean matches(CharSequence rawPassword, Password encodedPassword);

}
