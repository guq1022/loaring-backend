package com.lams.loaring.config.valid;

import com.lams.loaring.config.BaseEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValueOfEnumValidator implements ConstraintValidator<ValidEnum, BaseEnum> {

	private ValidEnum validEnum;

	@Override
	public void initialize(ValidEnum constraintAnnotation) {
		this.validEnum = constraintAnnotation;
	}

	@Override
	public boolean isValid(BaseEnum baseEnum, ConstraintValidatorContext context) {

		BaseEnum[] enumValues = this.validEnum.enumClass()
		                                      .getEnumConstants();

		if (enumValues == null) {
			return false;
		}

		if (baseEnum == null) {
			return false;
		}

		// this.enumValue.ignoreCase() && value.getKey().equalsIgnoreCase(enumValue.toString())
		// final boolean ignoreCase = this.validEnum.ignoreCase();
		for (BaseEnum enumValue : enumValues) {
			if (enumValue.equals(baseEnum)) {
				return true;
			}
		}

		return false;
	}

}
