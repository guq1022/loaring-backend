package com.lams.loaring.config.message;

import com.lams.loaring.config.dto.BaseErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseCode implements BaseErrorEnum {


	// GLOBAL
	GLOBAL_DEFAULT_OK_CODE("000", "global.default.ok.message"),
	GLOBAL_DEFAULT_FAIL_CODE("999", "global.default.fail.message"),
	GLOBAL_UNDEFINED_CODE("101", "global.undefined.code.message"),
	GLOBAL_RESOURCE_NOT_FOUND_CODE("102", "global.resource.notFound.message"),
	GLOBAL_ENTITY_NOT_FOUND_CODE("103", "global.entity.notfound.message"),

	// SECURITY
	SECURITY_FAIL_DEFAULT_CODE("200", "token.default.fail.message"),
	SECURITY_FAIL_CREDENTIAL_CODE("201", "security.fail.password.message"),
	SECURITY_FAIL_UNAUTHORIZED_CODE("202", "security.fail.unauthorized.message"),
	SECURITY_FAIL_DENIED_CODE("203", "security.fail.denied.message"),

	// TOKEN
	TOKEN_FAIL_DEFAULT_CODE("250", "token.default.fail.message"),
	TOKEN_FAIL_EXPIRED_CODE("251", "token.fail.expired.code"),
	TOKEN_FAIL_SIGNATURE_CODE("252", "token.fail.signature.code"),
	TOKEN_FAIL_MALFORMED_CODE("253", "token.fail.malformed.code"),
	TOKEN_FAIL_UNSUPPORTED_CODE("254", "token.fail.unsupported.code"),
	TOKEN_FAIL_EXPIRED_REPUBLISH_CODE("255", "token.fail.expired.republish.code"),
	;
	
	private final String code;
	private final String message;

	@Override
	public String getKey() {
		return name();
	}

}
