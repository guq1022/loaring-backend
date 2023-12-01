package com.lams.loaring.config.dto;

import com.lams.loaring.config.MessageUtils;
import com.lams.loaring.config.dto.BaseResponse.Headers;
import com.lams.loaring.config.exception.BaseBusinessException;
import com.lams.loaring.config.exception.BaseDefaultException;
import com.lams.loaring.config.exception.BaseEntityNotFoundException;
import com.lams.loaring.config.message.BaseCode;
import com.lams.loaring.config.utils.BaseMapperUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 *
 */
@Slf4j
@RequiredArgsConstructor
public class BaseResponseUtils {

	private final BaseMapperUtils baseMapperUtils;

	public BaseErrorResponse createMessage(BaseEntityNotFoundException exception) {
		var headers = createHeaders(exception.getBaseErrorCode(), exception.getArgs());
		exception.printStackTrace();
		return BaseErrorResponse.builder()
			.headers(headers)
			.build();
	}

	public BaseErrorResponse createMessage(BaseBusinessException exception) {
		var headers = createHeaders(exception.getBaseErrorCode(), exception.getArgs());
		exception.printStackTrace();
		return BaseErrorResponse.builder()
			.headers(headers)
			.build();
	}

	public BaseErrorResponse createMessage(BaseDefaultException exception) {
		var headers = createHeaders(exception.getBaseErrorCode(), exception.getArgs());
		exception.printStackTrace();
		return BaseErrorResponse.builder()
			.headers(headers)
			.build();
	}

	public BaseErrorResponse createMessage(Exception exception) {

		var headers = createHeaders(BaseCode.GLOBAL_DEFAULT_FAIL_CODE, null);

		exception.printStackTrace();

		var errorResponse = BaseErrorResponse.builder()
			.headers(headers);

		if (exception instanceof MethodArgumentNotValidException) {

			var errorValidResponses = ErrorValidResponse.ofList(
				(MethodArgumentNotValidException) exception);
			return errorResponse.errors(errorValidResponses)
				.build();
		}

		if (exception instanceof ConstraintViolationException) {
			return errorResponse.errors(
					ErrorValidResponse.ofList((ConstraintViolationException) exception))
				.build();
		}

		return errorResponse
			.build();

	}

	private Headers createHeaders(String status, String message, String[] args) {
		return Headers.builder()
			.code(status)
			.message(MessageUtils.getMessage(message, args))
			.build();
	}

	private Headers createHeaders(BaseCode baseCode, String[] args) {
		return createHeaders(baseCode.getCode(), baseCode.getMessage(),
			args);
	}

	public void sendResponse(HttpServletResponse response,
		HttpStatus status,
		BaseResponse baseResponse) {

		response.setCharacterEncoding("UTF-8");
		response.setStatus(status.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		try {

			response.getWriter()
				.write(baseMapperUtils.createErrorMessage(baseResponse));

		} catch (IOException ioException) {
			throw new BaseBusinessException();
		}

	}

	public void sendResponse(HttpServletResponse response, HttpStatus status) {
		sendResponse(response, status, BaseResponse.builder()
			.headers(createHeaders(BaseCode.GLOBAL_DEFAULT_OK_CODE, null))
			.build());
	}
}