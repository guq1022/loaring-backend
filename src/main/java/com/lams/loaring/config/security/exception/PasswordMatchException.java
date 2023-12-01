package com.lams.loaring.config.security.exception;

import com.lams.loaring.config.exception.BaseDefaultException;
import com.lams.loaring.config.message.BaseCode;

public class PasswordMatchException extends BaseDefaultException {

	public PasswordMatchException(String... args) {
		super(BaseCode.SECURITY_FAIL_CREDENTIAL_CODE, args);
	}

}
