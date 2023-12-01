package com.lams.loaring.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lams.loaring.adventurer.dto.LoginRequest;
import com.lams.loaring.config.security.token.JwtAuthenticationToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.io.IOException;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private final ObjectMapper objectMapper;
	private final Validator validator;

	public LoginAuthenticationFilter(ObjectMapper objectMapper,
		Validator validator,
		AntPathRequestMatcher antPathRequestMatcher) {
		super(antPathRequestMatcher);
		this.objectMapper = objectMapper;
		this.validator = validator;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
		HttpServletResponse response) throws AuthenticationException, IOException {
		LoginRequest loginRequest = toLoginRequest(request);

		Set<ConstraintViolation<LoginRequest>> validate = validator.validate(loginRequest);

		if (validate.isEmpty()) {
			Authentication token = createToken(loginRequest);
			return getAuthenticationManager().authenticate(token);
		}

		log.error("로그인 제약 조건에 문제가 있습니다. {}", "LoginRequest");
		throw new ConstraintViolationException(validate);
	}

	private LoginRequest toLoginRequest(HttpServletRequest request) throws IOException {
		return objectMapper.readValue(request.getReader(), LoginRequest.class);
	}

	private AbstractAuthenticationToken createToken(LoginRequest loginRequest) {
		return new JwtAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
	}
}
