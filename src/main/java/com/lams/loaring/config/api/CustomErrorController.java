package com.lams.loaring.config.api;

import com.lams.loaring.config.dto.BaseErrorResponse;
import com.lams.loaring.config.exception.BaseBusinessException;
import com.lams.loaring.config.message.BaseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequiredArgsConstructor
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	public ResponseEntity<BaseErrorResponse> error(Exception exception) {
		throw new BaseBusinessException(BaseCode.GLOBAL_DEFAULT_FAIL_CODE, exception);
	}

}
