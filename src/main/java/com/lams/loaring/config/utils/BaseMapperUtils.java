package com.lams.loaring.config.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lams.loaring.config.dto.BaseResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseMapperUtils {

	private final ObjectMapper objectMapper;

	public String createErrorMessage(BaseResponse message) {

		try {
			return objectMapper.writeValueAsString(message);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String createErrorMessage(String message) {

		try {
			return objectMapper.writeValueAsString(message);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}
}
