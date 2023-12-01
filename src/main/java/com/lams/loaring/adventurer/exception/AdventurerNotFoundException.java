package com.lams.loaring.adventurer.exception;

import com.lams.loaring.config.exception.BaseEntityNotFoundException;
import com.lams.loaring.config.message.BaseCode;

public class AdventurerNotFoundException extends BaseEntityNotFoundException {

	public AdventurerNotFoundException(BaseCode baseCode) {
		super(baseCode);
	}

	public AdventurerNotFoundException(String... args) {
		super(args);
	}
}
