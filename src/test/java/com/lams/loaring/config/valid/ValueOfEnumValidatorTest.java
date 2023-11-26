package com.lams.loaring.config.valid;

import com.lams.loaring.sample.domain.DefaultType;
import com.lams.loaring.sample.dto.SampleRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ValueOfEnumValidatorTest {

	private Validator validator;

	@BeforeEach
	void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void validEnumIsNull() {
		// given
		SampleRequest sampleRequest = SampleRequest.builder()
			.name("sample_이름")
			.defaultType(null)
			.build();

		// when
		Set<ConstraintViolation<SampleRequest>> violations = validator.validate(sampleRequest);

		// then
		Assertions.assertThat(violations.size())
			.isEqualTo(1);

	}

	@Test
	void validEnumIsEnumType() {

		// given
		SampleRequest sampleRequest = SampleRequest.builder()
			.name("sample_이름")
			.defaultType(DefaultType.GENERAL)
			.build();

		// when
		Set<ConstraintViolation<SampleRequest>> violations = validator.validate(sampleRequest);

		// then
		Assertions.assertThat(violations.size())
			.isEqualTo(0);
	}

	@Test
	void validStringIsNull() {

		// given
		SampleRequest sampleRequest = SampleRequest.builder()
			.name(null)
			.defaultType(DefaultType.GENERAL)
			.build();

		// when
		Set<ConstraintViolation<SampleRequest>> violations = validator.validate(sampleRequest);

		// then
		Assertions.assertThat(violations.size())
			.isEqualTo(1);
	}

}