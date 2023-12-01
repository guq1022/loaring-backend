package com.lams.loaring.config.security.token;

import com.lams.loaring.config.BaseEnum;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class JwtTokenConfig {

	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer";
	public static final String REFRESH_TOKEN = "refreshToken";

	private final long accessTokenExpireTime;
	private final long refreshTokenExpireTime;
	private final long reissueExpireTime;
	private final byte[] secretKey;

	private JwtTokenProvider jwtTokenProvider;

	public JwtTokenConfig(
		long accessTokenExpireTime,
		long refreshTokenExpireTime,
		long reissueExpireTime,
		String secretKey) {
		this.secretKey = secretKey.getBytes(StandardCharsets.UTF_8);
		this.accessTokenExpireTime = Duration.ofMinutes(accessTokenExpireTime)
			.toMillis();
		this.refreshTokenExpireTime = Duration.ofDays(refreshTokenExpireTime)
			.toMillis();
		this.reissueExpireTime = Duration.ofDays(reissueExpireTime)
			.toMillis();
	}

	@Getter
	@AllArgsConstructor
	public enum TokenType implements BaseEnum<String> {

		ACCESS("access", "액세스 토큰"),
		REFRESH("refresh", "리프레시 토큰");

		private final String code;
		private final String comment;

		@Override
		public String getKey() {
			return name();
		}
	}
	
}
