package com.lams.loaring.config.api;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/api/*")
public class RequestCachingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
			final var trxId = UUID.randomUUID().toString().substring(0,8);
			MDC.put(MDCKey.TRX_ID.getKey(), trxId);

			var cachedHttpServletRequest = new CachedHttpServletRequest(request);
			var cachedHttpServletResponse = new CachedHttpServletResponse(response);

			log.info("REQUEST URL : {}", request.getServletPath());

			if (isNotGETMethod(request)) {
				log.info("REQUEST DATA: {}", IOUtils.toString(cachedHttpServletRequest.getInputStream(), StandardCharsets.UTF_8));
			}
			filterChain.doFilter(cachedHttpServletRequest, cachedHttpServletResponse);
			log.info("RESPONSE DATA: {}", cachedHttpServletResponse.getContentString());
			cachedHttpServletResponse.copyBodyToResponse();
			MDC.clear();
	}

	private boolean isNotGETMethod(HttpServletRequest request) {
		return !HttpMethod.GET.matches(request.getMethod());
	}

	private static boolean isVisible(MediaType mediaType) {
		final List<MediaType> VISIBLE_TYPES = Arrays.asList(
			MediaType.valueOf("text/*"),
			MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML,
			MediaType.valueOf("application/*+json"),
			MediaType.valueOf("application/*+xml"),
			MediaType.MULTIPART_FORM_DATA
		);

		return VISIBLE_TYPES.stream()
			.anyMatch(visibleType -> visibleType.includes(mediaType));
	}

}
