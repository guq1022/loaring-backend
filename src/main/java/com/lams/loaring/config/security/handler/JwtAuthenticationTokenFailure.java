package com.lams.loaring.config.security.handler;

import com.lams.loaring.config.exception.BaseDefaultException;
import com.lams.loaring.config.message.BaseCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@RequiredArgsConstructor
public class JwtAuthenticationTokenFailure implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException exception) {
		throw new BaseDefaultException(BaseCode.SECURITY_FAIL_CREDENTIAL_CODE, exception);
	}
}
