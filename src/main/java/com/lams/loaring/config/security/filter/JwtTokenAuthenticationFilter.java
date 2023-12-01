package com.lams.loaring.config.security.filter;

import com.lams.loaring.adventurer.domain.AdventureContext;
import com.lams.loaring.config.message.BaseCode;
import com.lams.loaring.config.security.service.AdventurerService;
import com.lams.loaring.config.security.token.JwtTokenConfig;
import com.lams.loaring.config.security.token.JwtTokenUtils;
import com.lams.loaring.config.security.token.exception.TokenCheckFailException;
import com.lams.loaring.config.utils.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

	private final AdventurerService adventurerService;
	private final JwtTokenUtils jwtTokenUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		var authorization = request.getHeader(JwtTokenConfig.AUTHORIZATION);

		if (StringUtils.isBlank(authorization)) {
			filterChain.doFilter(request, response);
			return;
		}

		try {

			var email = jwtTokenUtils.parseToken(jwtTokenUtils.resolveToken(authorization));
			setAuthentication(email);

		} catch (TokenCheckFailException e) {

			if (BaseCode.TOKEN_FAIL_EXPIRED_CODE.equals(e.getBaseErrorCode())) {

				var headerRefreshToken = request.getHeader(JwtTokenConfig.REFRESH_TOKEN);

				var refreshToken = jwtTokenUtils.resolveToken(headerRefreshToken);

				if (StringUtils.isBlank(refreshToken)) {
					filterChain.doFilter(request, response);
					return;
				}

				var email = jwtTokenUtils.parseToken(refreshToken);
				var jwtToken = adventurerService.reissue(refreshToken, email);

				jwtTokenUtils.setHeaderAccessToken(response, jwtToken.getAccessToken());
				jwtTokenUtils.setHeaderRefreshToken(response, jwtToken.getRefreshToken());
				throw new TokenCheckFailException(BaseCode.TOKEN_FAIL_EXPIRED_REPUBLISH_CODE, e);

			}

			throw e;
		}

		filterChain.doFilter(request, response);
	}

	private void setAuthentication(String email) {
		var adventureContext = adventurerService.findByAdventurer(email);
		SecurityContextHolder.getContext()
			.setAuthentication(createToken(adventureContext));
	}

	private UsernamePasswordAuthenticationToken createToken(AdventureContext adventureContext) {
		return new UsernamePasswordAuthenticationToken(adventureContext, null,
			adventureContext.getAuthorities());
	}
}
