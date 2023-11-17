package com.lams.loaring.config.advice.exception;

public class BaseBusinessException extends RuntimeException {

	public static final String MESSAGE_BUSINESS_EXCEPTION = "요청 중 오류가 발생하였습니다. 잠시 후 다시 시도해주시기 바랍니다.";

	public BaseBusinessException() {
		super(MESSAGE_BUSINESS_EXCEPTION);
	}

	public BaseBusinessException(String message) {
		super(message);
	}
}
