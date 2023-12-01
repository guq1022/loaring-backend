package com.lams.loaring.adventurer.domain;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class Password {

	public static final String EMPTY_PASSWORD = "";

	private String password = EMPTY_PASSWORD;

	public static Password of(final String password) {
		return new Password(password);
	}

}
