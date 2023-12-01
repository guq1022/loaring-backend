package com.lams.loaring.config.security.token.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken {

	private String accessToken;
	private String refreshToken;

}
