package com.lams.loaring.config.advice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ErrorResponse {

	private String errorMessage;

	public static ErrorResponse of(String errorMessage) {
		return new ErrorResponse(errorMessage);
	}

}
