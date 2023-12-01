package com.lams.loaring.config.api.filter;

import com.lams.loaring.config.api.CachedHttpServletRequest;
import com.lams.loaring.config.api.MDCKey;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
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
		final var trxId = UUID.randomUUID()
			.toString()
			.substring(0, 8);
		MDC.put(MDCKey.TRX_ID.getKey(), trxId);

		var cachedHttpServletRequest = new CachedHttpServletRequest(request);
		log.info("REQUEST URL : {}", request.getServletPath());

		if (isNotGETMethod(request)) {
			log.info("REQUEST DATA: {}", IOUtils.toString(cachedHttpServletRequest.getInputStream(),
				StandardCharsets.UTF_8));
		}

		filterChain.doFilter(cachedHttpServletRequest, response);
	}

	private boolean isNotGETMethod(HttpServletRequest request) {
		return !HttpMethod.GET.matches(request.getMethod());
	}

}
