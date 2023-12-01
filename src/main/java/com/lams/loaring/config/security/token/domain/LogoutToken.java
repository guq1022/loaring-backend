package com.lams.loaring.config.security.token.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Data
@Builder
@RedisHash("logoutToken")
@NoArgsConstructor
@AllArgsConstructor
public class LogoutToken {

	@Id
	private String id;

	private String username;

	@TimeToLive
	private Long expiration;

	public static LogoutToken from(String username, String accessToken, Long expirationTime) {
		return LogoutToken.builder()
			.id(accessToken)
			.username(username)
			.expiration(expirationTime / 1000)
			.build();
	}
}