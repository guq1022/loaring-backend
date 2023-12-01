package com.lams.loaring.config.security.token.exception;

import com.lams.loaring.config.exception.BaseDefaultException;
import com.lams.loaring.config.message.BaseCode;

public class TokenCheckFailException extends BaseDefaultException {

	public TokenCheckFailException() {
	}

	public TokenCheckFailException(BaseCode baseCode) {
		super(baseCode);
	}

	public TokenCheckFailException(BaseCode baseCode, String... args) {
		super(baseCode, args);
	}

	public TokenCheckFailException(RuntimeException exception) {
		super(exception);
	}

	public TokenCheckFailException(BaseCode baseCode,
		RuntimeException runtimeException) {
		super(baseCode, runtimeException);
	}

	public TokenCheckFailException(BaseCode baseCode,
		Exception exception) {
		super(baseCode, exception);
	}
}
