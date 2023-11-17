package com.lams.loaring.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

@SpringBootTest
class MessageUtilsTest {

	@Autowired
	MessageSource messageSource;

	@Test
	void newInstance() {
		String message = MessageUtils.of("default.message");
		assertThat(message)
			.isEqualTo("관리자에게 문의하여주세요");
	}


	@Test
	void notBlank() {
		String message = MessageUtils.of("NotBlank");
		assertThat(message)
			.isEqualTo("공백일 수 없습니다!");
	}

}