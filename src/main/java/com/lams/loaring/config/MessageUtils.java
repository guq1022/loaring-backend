package com.lams.loaring.config;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageUtils {

	private final MessageSource messageSource;

	private static MessageSource staticMessageSource;

	@PostConstruct
	private void init() {
		staticMessageSource = this.messageSource;
	}

	public static String of(@NotEmpty String messageCode) {
		return staticMessageSource.getMessage(messageCode, null, "정의되지 않은 코드입니다", Locale.KOREA);
	}

	public String getMessage(@NotEmpty String messageCode, String... params) {
		return messageSource.getMessage(messageCode, params, "정의되지 않은 코드입니다", Locale.KOREA);
	}

	/**
	 * 특정 지역을 정의하여 Message 처리
	 *
	 * @param messageCode messageCode
	 * @param locale      지역
	 * @param params      Message Param
	 * @return 다국어 메세지 텍스트
	 */
	public String getMessage(@NotEmpty String messageCode, @NotNull Locale locale,
		String... params) {
		return messageSource.getMessage(messageCode, params, "정의되지 않은 코드입니다", locale);
	}

}
