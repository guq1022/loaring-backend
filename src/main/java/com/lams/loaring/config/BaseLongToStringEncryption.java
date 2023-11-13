package com.lams.loaring.config;

import java.util.Base64;
import org.springframework.stereotype.Component;


/**
 *
 * @since 2023.11.13
 * @author vic
 * @version 1.0
 *
 * @see java.util.Base64
 *
 */

@Component
public class BaseLongToStringEncryption implements BaseEncryption<Long, String> {

	@Override
	public String encrypt(Long value) {
		return Base64.getEncoder().encodeToString(value.toString().getBytes());
	}

	@Override
	public Long decrypt(String encryptedValue) {
		byte[] decode = Base64.getDecoder().decode(encryptedValue);
		return Long.valueOf(new String(decode));
	}

}
