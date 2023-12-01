package com.lams.loaring.config.exception;

import com.lams.loaring.config.message.BaseCode;

public class BaseDefaultException extends RuntimeException implements BaseException {

	private static final BaseCode BASE_MESSAGE_CODE = BaseCode.GLOBAL_DEFAULT_FAIL_CODE;

	private final BaseCode baseCode;
	private final Exception runtimeException;
	private String[] args;

	public BaseDefaultException() {
		super(BASE_MESSAGE_CODE.getMessage());
		this.baseCode = BASE_MESSAGE_CODE;
		this.runtimeException = this;
	}

	public BaseDefaultException(BaseCode baseCode) {
		super(baseCode.getMessage());
		this.baseCode = baseCode;
		this.runtimeException = this;
	}

	public BaseDefaultException(BaseCode baseCode, String... args) {
		super(baseCode.getMessage());
		this.baseCode = baseCode;
		this.runtimeException = this;
		this.args = args;
	}

	public BaseDefaultException(RuntimeException exception) {
		super(BASE_MESSAGE_CODE.getMessage());
		this.baseCode = BASE_MESSAGE_CODE;
		this.runtimeException = exception;
	}

	public BaseDefaultException(BaseCode baseCode,
		RuntimeException runtimeException) {
		super(baseCode.getMessage());
		this.baseCode = baseCode;
		this.runtimeException = runtimeException;
	}

	public BaseDefaultException(BaseCode baseCode, Exception exception) {
		super(baseCode.getMessage());
		this.baseCode = baseCode;
		this.runtimeException = exception;
	}

	@Override
	public String[] getArgs() {
		return this.args;
	}

	@Override
	public String getCode() {
		return this.baseCode.getCode();
	}

	@Override
	public BaseCode getBaseErrorCode() {
		return this.baseCode;
	}
}
