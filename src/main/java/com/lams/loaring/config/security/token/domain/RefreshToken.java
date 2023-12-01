package com.lams.loaring.config.security.token.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("refreshToken")
public class RefreshToken {

	@Id
	private String id;

	private String refreshToken;

	@TimeToLive
	private Long expiration;

	public static RefreshToken from(String username, String refreshToken, Long expirationTime) {
		return RefreshToken.builder()
			.id(username)
			.refreshToken(refreshToken)
			.expiration(expirationTime / 1000)
			.build();
	}
}
