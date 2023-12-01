package com.lams.loaring.config.a.api;

import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorFactory;
import jakarta.validation.MessageInterpolator;
import jakarta.validation.ParameterNameProvider;
import jakarta.validation.TraversableResolver;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorContext;
import jakarta.validation.ValidatorFactory;
import org.springframework.context.MessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class BaseValidatorFactory {

	public static Validator of(
		ValidatorFactory validatorFactory,
		MessageSource messageSource) {
		return init(validatorFactory, messageSource).getValidator();
	}

	private static ValidatorFactory init(
		ValidatorFactory validatorFactory,
		MessageSource messageSource) {

		return new ValidatorFactory() {
			@Override
			public Validator getValidator() {
				LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
				bean.setValidationMessageSource(messageSource);
				bean.afterPropertiesSet();
				return bean;
			}

			@Override
			public ValidatorContext usingContext() {
				return validatorFactory.usingContext();
			}

			@Override
			public MessageInterpolator getMessageInterpolator() {
				return validatorFactory.getMessageInterpolator();
			}

			@Override
			public TraversableResolver getTraversableResolver() {
				return validatorFactory.getTraversableResolver();
			}

			@Override
			public ConstraintValidatorFactory getConstraintValidatorFactory() {
				return validatorFactory.getConstraintValidatorFactory();
			}

			@Override
			public ParameterNameProvider getParameterNameProvider() {
				return validatorFactory.getParameterNameProvider();
			}

			@Override
			public ClockProvider getClockProvider() {
				return validatorFactory.getClockProvider();
			}

			@Override
			public <T> T unwrap(Class<T> type) {
				return validatorFactory.unwrap(type);
			}

			@Override
			public void close() {
				validatorFactory.close();
			}
		};

	}
}
