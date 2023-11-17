package com.lams.loaring.config.advice.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class BaseRestControllerAdvice {

	public static final String ERRORS = "errors";

	private final ObjectMapper objectMapper;

	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<String> methodValidException(MethodArgumentNotValidException e)
		throws JsonProcessingException {
		String error = objectMapper.writer()
			.withRootName(ERRORS)
			.writeValueAsString(ErrorValidResponse.ofList(e));
		log.error(e.getMessage());
		return ResponseEntity.badRequest()
			.body(error);
	}

	@ExceptionHandler({BaseBusinessException.class})
	public ResponseEntity<ErrorResponse> baseBusinessException(Exception e) {
		log.debug(e.getMessage());
		return ResponseEntity.internalServerError()
			.body(ErrorResponse.of(e.getMessage()));
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<ErrorResponse> exception(Exception e) {
		log.error(e.getMessage());
		e.printStackTrace();
		return ResponseEntity.internalServerError()
			.body(ErrorResponse.of("관리자에게 문의하여주세요"));
	}

	@ExceptionHandler({EntityNotFoundException.class, ConstraintViolationException.class})
	public ResponseEntity<ErrorResponse> entityNotFoundException(Exception e) {
		log.debug(e.getMessage());
		return ResponseEntity.internalServerError()
			.body(ErrorResponse.of(e.getMessage()));
	}
}
