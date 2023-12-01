package com.lams.loaring.adventurer.api;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

import com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.restassured.RestDocumentationFilter;

public class LoginDocument {

	public static RestDocumentationFilter loginDocument() {
		return RestAssuredRestDocumentationWrapper.document(
			"로그인",
			requestFields(
				fieldWithPath("email").type(JsonFieldType.STRING)
					.description("이메일"),
				fieldWithPath("password").type(JsonFieldType.STRING)
					.description("패스워드"),
				fieldWithPath("snsType").type(JsonFieldType.STRING)
					.description("SNS타입")
			),
			responseHeaders(headerWithName("content-type").description("application/json")),
			responseFields(
				fieldWithPath("headers.code").type(JsonFieldType.STRING)
					.description("응답 코드"),
				fieldWithPath("headers.message").type(JsonFieldType.STRING)
					.description("응답 메시지"),
				fieldWithPath("data").type(JsonFieldType.OBJECT)
					.description("데이터")
			)
		);
	}

}
