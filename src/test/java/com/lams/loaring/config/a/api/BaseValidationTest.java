package com.lams.loaring.config.a.api;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class BaseValidationTest {

	private static final jakarta.validation.ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private static final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	protected static final Validator validator = BaseValidatorFactory.of(validatorFactory,
		messageSource);

	static {
		messageSource.setBasenames(
			"classpath:/messages/message",
			"classpath:/messages/validations/validation");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(60);
	}


}
