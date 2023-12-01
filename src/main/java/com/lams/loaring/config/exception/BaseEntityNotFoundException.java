package com.lams.loaring.config.exception;

import com.lams.loaring.config.message.BaseCode;
import jakarta.persistence.EntityNotFoundException;

public class BaseEntityNotFoundException extends EntityNotFoundException implements BaseException {

	private static final BaseCode ENTITY_NOT_FOUND_CODE = BaseCode.GLOBAL_ENTITY_NOT_FOUND_CODE;

	private final BaseCode baseCode;
	private String[] args;

	public BaseEntityNotFoundException(BaseCode baseCode) {
		super(baseCode.getMessage());
		this.baseCode = baseCode;
	}

	public BaseEntityNotFoundException(String... args) {
		super(ENTITY_NOT_FOUND_CODE.getMessage());
		this.baseCode = ENTITY_NOT_FOUND_CODE;
		this.args = args;
	}

	@Override
	public String[] getArgs() {
		return args;
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
