package com.lams.loaring.adventurer.api;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.lams.loaring.adventurer.domain.Account.SnsType;
import com.lams.loaring.adventurer.dto.LoginRequest;
import com.lams.loaring.config.a.api.BaseControllerTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class JwtTokenController extends BaseControllerTest {


	@Test
	@DisplayName("로그인됨")
	void loginWithGeneral() {

		//given

		LoginRequest loginRequest = LoginRequest.builder()
			.email("user1@gmail.com")
			.password("1111")
			.snsType(SnsType.NONE)
			.build();

		//when
		final Response response = customGivenWithDocs(LoginDocument.loginDocument()).body(loginRequest)
			.post("/api/login");

		//then
		assertSoftly(softAssertions -> {
			assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
			assertThat(response.header("refresh")).isNotNull();
			assertThat(response.header("access")).isNotNull();
		});
	}


}
