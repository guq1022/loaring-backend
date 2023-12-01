package com.lams.loaring.config.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class BaseResponse {

	protected static final BaseData baseData = new BaseData();

	private Headers headers;

	@Builder.Default
	private Object data = baseData;

	@Setter
	@Getter
	@SuperBuilder
	public static class Headers {

		private String code;
		private String message;
	}

	public static class BaseData {

	}
}
