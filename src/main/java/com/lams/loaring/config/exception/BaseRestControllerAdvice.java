package com.lams.loaring.config.exception;

import com.lams.loaring.config.dto.BaseErrorResponse;
import com.lams.loaring.config.dto.BaseResponseUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class BaseRestControllerAdvice {

	private final BaseResponseUtils baseResponseUtils;

	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<BaseErrorResponse> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {

		var headers = new HttpHeaders();
		headers.add(HttpHeaders.ALLOW, StringUtils.join(exception.getSupportedMethods()));

		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
		                     .headers(headers)
		                     .body(baseResponseUtils.createMessage(exception));
	}

	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<BaseErrorResponse> baseBusinessException(IllegalArgumentException exception) {
		return ResponseEntity.badRequest()
		                     .body(baseResponseUtils.createMessage(exception));
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<BaseErrorResponse> methodValidException(MethodArgumentNotValidException exception) {
		return ResponseEntity.badRequest()
		                     .body(baseResponseUtils.createMessage(exception));
	}

	@ExceptionHandler({BaseBusinessException.class})
	public ResponseEntity<BaseErrorResponse> baseBusinessException(Exception exception) {
		return ResponseEntity.ok()
		                     .body(baseResponseUtils.createMessage(exception));
	}

	@ExceptionHandler({EntityNotFoundException.class})
	public ResponseEntity<BaseErrorResponse> entityNotFoundException(BaseEntityNotFoundException exception) {
		return ResponseEntity.ok()
		                     .body(baseResponseUtils.createMessage(exception));
	}

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<BaseErrorResponse> constraintViolationException(ConstraintViolationException exception) {
		return ResponseEntity.internalServerError()
		                     .body(baseResponseUtils.createMessage(exception));
	}

	@ExceptionHandler({BaseDefaultException.class})
	public ResponseEntity<BaseErrorResponse> baseDefaultException(BaseDefaultException exception) {
		return ResponseEntity.internalServerError()
		                     .body(baseResponseUtils.createMessage(exception));
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<BaseErrorResponse> exception(Exception exception) {
		return ResponseEntity.internalServerError()
		                     .body(baseResponseUtils.createMessage(exception));
	}

}
