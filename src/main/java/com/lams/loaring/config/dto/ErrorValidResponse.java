package com.lams.loaring.config.dto;

import static lombok.AccessLevel.PRIVATE;

import com.lams.loaring.config.MessageUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class ErrorValidResponse {

	private static final String HANGLE_PATTERN = ".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*";
	private String field;
	private Object rejectedValue;
	private String defaultMessage;


	public static ErrorValidResponse of(FieldError fieldError) {

		return of(fieldError.getField(),
			fieldError.getRejectedValue(),
			Objects.requireNonNull(fieldError.getDefaultMessage()));
	}

	public static ErrorValidResponse of(String field,
		Object rejectedValue,
		String defaultMessage) {

		if (defaultMessage.matches(HANGLE_PATTERN)) {
			return new ErrorValidResponse(field, rejectedValue,
				defaultMessage);
		}

		return new ErrorValidResponse(field, rejectedValue,
			MessageUtils.getMessage(defaultMessage));
	}

	public static List<ErrorValidResponse> ofList(MethodArgumentNotValidException e) {

		BindingResult bindingResult = e.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		if (fieldErrors.isEmpty()) {
			return Collections.emptyList();
		}

		return fieldErrors.stream()
			.map(ErrorValidResponse::of)
			.toList();
	}

	public static List<ErrorValidResponse> ofList(ConstraintViolationException e) {

		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

		if (constraintViolations.isEmpty()) {
			return Collections.emptyList();
		}

		return constraintViolations.stream()
			.map(constraintViolation -> {

				return of(
					constraintViolation.getPropertyPath()
						.toString(),
					constraintViolation.getInvalidValue(),
					constraintViolation.getMessage()
				);

			})
			.toList();
	}

}
