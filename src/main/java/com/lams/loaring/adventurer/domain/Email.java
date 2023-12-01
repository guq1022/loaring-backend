package com.lams.loaring.adventurer.domain;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class Email {

	private static final String EMAIL_REGEX = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	private String email;

	public static Email of(String paramEmail) {
		var email = Objects.requireNonNullElse(paramEmail, "");

		if (isEmail(email)) {
			return new Email(email);
		}

		throw new IllegalArgumentException("field.constraints.Email.message");
	}

	private static boolean isEmail(String email) {
		var matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}


}
