package com.lams.loaring.config.exception;

import com.lams.loaring.config.message.BaseCode;

public interface BaseException {

	String[] getArgs();

	String getCode();

	BaseCode getBaseErrorCode();
}
