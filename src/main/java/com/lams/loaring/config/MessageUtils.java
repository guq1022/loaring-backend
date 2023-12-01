package com.lams.loaring.config;

import com.lams.loaring.config.message.BaseCode;
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

	public static String getMessage(@NotEmpty String messageCode) {
		return staticMessageSource.getMessage(messageCode, null, Locale.KOREA);
	}

	public static String getMessage(@NotEmpty BaseCode baseCode) {
		return staticMessageSource.getMessage(baseCode.getMessage(), null, Locale.KOREA);
	}

	public static String getMessage(@NotEmpty String messageCode, String... params) {
		return staticMessageSource.getMessage(messageCode, params, Locale.KOREA);
	}

	public static String getMessage(@NotEmpty String messageCode, @NotNull Locale locale,
		String... params) {
		return staticMessageSource.getMessage(messageCode, params, locale);
	}

}
