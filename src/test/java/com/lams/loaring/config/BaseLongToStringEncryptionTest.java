package com.lams.loaring.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class BaseLongToStringEncryptionTest {

	private static final Long NUMBER = 1L;
	private static final String STRING_NUMBER = String.valueOf(NUMBER);
	private BaseLongToStringEncryption baseEncryption;
	private String encryptStr = "";

	@BeforeAll
	void beforeAll() {
		baseEncryption = new BaseLongToStringEncryption();
	}

	@Test
	@Order(1)
	void 문자열_암호화() {
		encryptStr = baseEncryption.encrypt(NUMBER);
		assertThat(encryptStr).isNotEqualTo(STRING_NUMBER);
	}

	@Test
	@Order(2)
	void 문자열_정수_복호화() {
		var decryptStr = baseEncryption.decrypt(encryptStr);
		assertThat(decryptStr).isEqualTo(NUMBER);
	}

}