package com.lams.loaring.adventurer.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class EmailTest {
	
	@Test
	@DisplayName("이메일 생성")
	void of() {
		var email = Email.of("abc@gmail.com");
		assertThat(email).isNotNull();
	}

	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("이메일 값이 null or empty")
	void isNotEmail(String paramEmail) {
		assertThatThrownBy(() -> {
			Email.of(paramEmail);
		})
			.isExactlyInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("이메일 형태가 아닌 경우 예외")
	void isEmpty() {
		assertThatThrownBy(() -> {
			Email.of("abc");
		})
			.isExactlyInstanceOf(IllegalArgumentException.class);
	}


}