package com.lams.loaring.config.security.token;

import com.lams.loaring.config.exception.BaseException;
import com.lams.loaring.config.message.BaseCode;
import com.lams.loaring.config.security.token.JwtTokenConfig.TokenType;
import com.lams.loaring.config.security.token.exception.TokenCheckFailException;
import com.lams.loaring.config.utils.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO: 보안 변경 필요
 */
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {

	private final JwtTokenConfig jwtTokenConfig;

	public String createToken(String payload, TokenType tokenType) {

		if (TokenType.ACCESS.equals(tokenType)) {
			return Jwts.builder()
				.setClaims(
					createClaims(payload, jwtTokenConfig::getAccessTokenExpireTime))
				.signWith(createKey(), SignatureAlgorithm.HS256)
				.compact();
		}

		if (TokenType.REFRESH.equals(tokenType)) {
			return Jwts.builder()
				.setClaims(createClaims(payload, jwtTokenConfig::getRefreshTokenExpireTime))
				.signWith(createKey(), SignatureAlgorithm.HS256)
				.compact();
		}

		throw new TokenCheckFailException();
	}
	

	public Claims validateToken(String token) {
		validIsNotNull(token, TokenCheckFailException::new);
		try {
			return parseClaims(token);
		} catch (ExpiredJwtException e) {
			throw new TokenCheckFailException(BaseCode.TOKEN_FAIL_EXPIRED_CODE);
		} catch (UnsupportedJwtException e) {
			throw new TokenCheckFailException(BaseCode.TOKEN_FAIL_UNSUPPORTED_CODE);
		} catch (MalformedJwtException e) {
			throw new TokenCheckFailException(BaseCode.TOKEN_FAIL_MALFORMED_CODE);
		} catch (SignatureException e) {
			throw new TokenCheckFailException(BaseCode.TOKEN_FAIL_SIGNATURE_CODE);
		}
	}

	/**
	 * 토큰을 해독하여 토큰에 담긴 정보를 가져온다.
	 */
	public Claims parseClaims(String token) {
		validIsNotNull(token, TokenCheckFailException::new);
		JwtParser jwtParser = Jwts.parserBuilder()
			.setSigningKey(createKey())
			.build();

		return jwtParser
			.parseClaimsJws(token)
			.getBody();
	}

	private void validIsNotNull(String token, Supplier<BaseException> consumer) {
		if (StringUtils.isBlank(token)) {
			consumer.get();
		}
	}

	private Claims createClaims(String payload, Supplier<Long> tokenValidTime) {

		final var currentTimeMillis = System.currentTimeMillis();
		Date date = new Date(currentTimeMillis);
		Date expiration = new Date(currentTimeMillis + tokenValidTime.get());

		return Jwts.claims()
			.setIssuedAt(date)
			.setSubject(payload)
			.setExpiration(expiration);
	}

	public Key createKey() {
		var encodeKey = Base64.getEncoder()
			.encode(jwtTokenConfig.getSecretKey());
		return Keys.hmacShaKeyFor(encodeKey);
	}

}
