package com.lams.loaring.config.exception;

import com.lams.loaring.config.message.BaseCode;

public class BaseBusinessException extends RuntimeException implements BaseException {

	private static final BaseCode BASE_MESSAGE_CODE = BaseCode.GLOBAL_DEFAULT_FAIL_CODE;

	private final BaseCode baseCode;
	private final Exception runtimeException;
	private String[] args;


	public static BaseBusinessException of() {
		return new BaseBusinessException();
	}

	public static BaseBusinessException of(BaseCode baseCode, Exception exception) {
		return new BaseBusinessException(baseCode, exception);
	}

	public static BaseBusinessException of(BaseCode baseCode,
		RuntimeException runtimeException) {
		return new BaseBusinessException(baseCode, runtimeException);
	}

	public static BaseBusinessException of(BaseCode baseCode) {
		return new BaseBusinessException(baseCode);
	}

	public BaseBusinessException() {
		super(BASE_MESSAGE_CODE.getMessage());
		this.baseCode = BASE_MESSAGE_CODE;
		this.runtimeException = this;
	}

	public BaseBusinessException(BaseCode baseCode) {
		super(baseCode.getMessage());
		this.baseCode = baseCode;
		this.runtimeException = this;
	}

	public BaseBusinessException(RuntimeException exception) {
		super(BASE_MESSAGE_CODE.getMessage());
		this.baseCode = BASE_MESSAGE_CODE;
		this.runtimeException = exception;
	}

	public BaseBusinessException(BaseCode baseCode,
		RuntimeException runtimeException) {
		super(baseCode.getMessage());
		this.baseCode = baseCode;
		this.runtimeException = runtimeException;
	}

	public BaseBusinessException(BaseCode baseCode, Exception exception) {
		super(baseCode.getMessage());
		this.baseCode = baseCode;
		this.runtimeException = exception;
	}

	public BaseBusinessException(BaseCode baseCode, String... args) {
		super(baseCode.getMessage());
		this.baseCode = baseCode;
		this.runtimeException = this;
		this.args = args;
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
