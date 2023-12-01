package com.lams.loaring.adventurer.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.lams.loaring.config.a.api.BaseValidationTest;
import jakarta.validation.ConstraintViolation;
import java.util.Set;
import org.junit.jupiter.api.Test;


class AdventurerRegisterRequestTest extends BaseValidationTest {


	@Test
	void 필수파라미터_검증() {
		// given
		AdventurerRegisterRequest adventurerRegisterRequest = AdventurerRegisterRequest.builder()
			.email("")
			.password("")
			.accountType(null)
			.snsType(null)
			.build();

		// when
		Set<ConstraintViolation<AdventurerRegisterRequest>> violations = validator.validate(
			adventurerRegisterRequest);

		// then
		assertThat(violations.size())
			.isEqualTo(4);
	}

//	@Test
//	void 이메일_형식이_아닌경우_예외() {
//		// given
//		AdventurerRequest adventurerRequest = AdventurerRequest.builder()
//			.email("abc")
//			.password("123")
//			.accountType(null)
//			.snsType(SnsType.KAKAO)
//			.build();
//
//		// when
//		Set<ConstraintViolation<AdventurerRequest>> violations = validator.validate(
//			adventurerRequest);
//
//		// then
//		assertThat(violations.size()).isEqualTo(2);
//

//	}
}