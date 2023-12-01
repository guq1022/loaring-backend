package com.lams.loaring.config.security.token;

import com.lams.loaring.config.security.token.JwtTokenConfig.TokenType;
import com.lams.loaring.config.security.token.domain.RefreshToken;
import com.lams.loaring.config.utils.StringUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenUtils {

	private final JwtTokenConfig jwtTokenConfig;
	private final JwtTokenProvider jwtTokenProvider;

	public String parseToken(String token) {
		return jwtTokenProvider.validateToken(token)
			.getSubject();
	}

	public String resolveToken(String token) {
		if (StringUtils.isNotBlank(token) && token.startsWith(JwtTokenConfig.BEARER)) {
			return token.substring(JwtTokenConfig.BEARER.length());
		}
		return null;
	}

	public boolean lessThanReissueExpirationTimesLeft(String refreshToken) {
		return getRemainTime(refreshToken) < jwtTokenConfig.getReissueExpireTime();
	}

	public long getRemainTime(String token) {
		Date expiration = jwtTokenProvider.parseClaims(token)
			.getExpiration();
		Date now = new Date();
		return expiration.getTime() - now.getTime();
	}


	public String createAccessToken(String payload) {
		return jwtTokenProvider.createToken(payload, TokenType.ACCESS);
	}

	public String createRefreshToken(String payload) {
		return jwtTokenProvider.createToken(payload, TokenType.REFRESH);
	}

	public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
		response.setHeader(TokenType.ACCESS.getCode(),
			String.format("%s %s", JwtTokenConfig.BEARER, accessToken));
	}

	public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
		response.setHeader(TokenType.REFRESH.getCode(),
			String.format("%s %s", JwtTokenConfig.BEARER, refreshToken));
	}

	public void setRefreshTokenAtCookie(RefreshToken refreshToken) {
		Cookie cookie = new Cookie(JwtTokenConfig.REFRESH_TOKEN, refreshToken.getRefreshToken());
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setMaxAge(refreshToken.getExpiration()
			.intValue());
		HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(
			RequestContextHolder.getRequestAttributes())).getResponse();
		assert response != null;
		response.addCookie(cookie);
	}

}
