package com.lams.loaring.adventurer.domain;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import com.lams.loaring.config.BaseEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class Account implements Serializable {

	@Enumerated(EnumType.STRING)
	private AccountType accountType = AccountType.GENERAL;

	@Enumerated(EnumType.STRING)
	private SnsType snsType = SnsType.NONE;

	public static Account Default() {
		return createGeneralAccount();
	}

	public static Account of(SnsType snsType) {

		return isSNS(snsType)
			? createSNSAccount(snsType)
			: createGeneralAccount();
	}

	public static boolean isSNS(SnsType snsType) {
		return SnsType.NONE.equals(snsType);
	}

	public static Account createSNSAccount(SnsType snsType) {
		return new Account(AccountType.SNS, snsType);
	}

	public static Account createGeneralAccount() {
		return new Account(AccountType.GENERAL, SnsType.NONE);
	}

	@Getter
	@AllArgsConstructor
	public enum SnsType implements BaseEnum<String> {

		KAKAO("kakao", "카카오"),
		DISCORD("discord", "디스코드"),
		NONE("none", "일반가입");

		private final String code;
		private final String comment;

		@Override
		public String getKey() {
			return name();
		}

	}

	@Getter
	@AllArgsConstructor
	public enum AccountType implements BaseEnum<String> {
		GENERAL("general", "일반"),
		SNS("sns", "SNS");

		private final String code;
		private final String comment;

		@Override
		public String getKey() {
			return name();
		}

	}
}
