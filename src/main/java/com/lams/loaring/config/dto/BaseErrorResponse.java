package com.lams.loaring.config.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class BaseErrorResponse extends BaseResponse {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<ErrorValidResponse> errors;

	public BaseErrorResponse(Headers headers, Object data, List<ErrorValidResponse> errors) {
		super(headers, data);
		this.errors = errors;
	}

}
