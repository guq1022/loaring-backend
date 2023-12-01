package com.lams.loaring.adventurer.domain;

class AdventurerTest {

//	static Stream<Arguments> 계정타입_회원가입_파라미터() {
//		return Stream.of(
//			arguments("일반", AccountType.GENERAL, SnsType.NONE),
//			arguments("카카오", AccountType.SNS, SnsType.KAKAO),
//			arguments("디스코드", AccountType.SNS, SnsType.DISCORD)
//		);
//	}
//
//
//	@ParameterizedTest
//	@MethodSource("계정_타입별_회원가입_파라미터")
//	void 모험가_가입(String type, AccountType accountType, SnsType snsType) {
//
//		// given
//		final var email = "seunghoo@gmail.com";
//		final var password = "password";
//
//		final var adventurerRequest = AdventurerRequest.builder()
//			.email(email)
//			.password(password)
//			.accountType(accountType)
//			.snsType(snsType)
//			.build();
//
//		// when
//		final var 모험가 = Adventurer.of(adventurerRequest);
//
//		// then
//		assertThat(모험가.match(adventurerRequest))
//			.isTrue();
//	}
//
//	static Stream<Arguments> 계정타입_실패_파라미터() {
//		return Stream.of(
//			arguments("일반", AccountType.GENERAL, SnsType.KAKAO),
//			arguments("디스코드", AccountType.GENERAL, SnsType.DISCORD),
//			arguments("카카오", AccountType.SNS, SnsType.NONE)
//		);
//	}
//
//	@ParameterizedTest
//	@MethodSource("계정타입_실패_파라미터")
//	void 가입시_계정타입이_다른경우_예외(String type, AccountType accountType, SnsType snsType) {
//
//		// given
//		final var email = "seunghoo@gmail.com";
//		final var password = "password";
//
//		final var adventurerRequest = AdventurerRequest.builder()
//			.email(email)
//			.password(password)
//			.accountType(accountType)
//			.snsType(snsType)
//			.build();
//
//		assertThatThrownBy(() -> {
//			Adventurer.of(adventurerRequest);
//		})
//			.isExactlyInstanceOf(IllegalArgumentException.class);
//
//
//	}


}