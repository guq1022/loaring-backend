package com.lams.loaring.config.exception.filter;

import com.lams.loaring.config.api.CachedHttpServletResponse;
import com.lams.loaring.config.dto.BaseResponseUtils;
import com.lams.loaring.config.exception.BaseBusinessException;
import com.lams.loaring.config.exception.BaseDefaultException;
import com.lams.loaring.config.exception.BaseEntityNotFoundException;
import com.lams.loaring.config.message.BaseCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@RequiredArgsConstructor
@WebFilter(filterName = "ExceptionHandlerFilter", urlPatterns = "/api/*")
public class ExceptionHandlerFilter extends OncePerRequestFilter {

	private final BaseResponseUtils baseResponseUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {

		var cachedHttpServletResponse = new CachedHttpServletResponse(response);

		try {

			filterChain.doFilter(
				request,
				cachedHttpServletResponse
			);

		} catch (BaseBusinessException e) {

			baseResponseUtils.sendResponse(
				cachedHttpServletResponse,
				HttpStatus.OK,
				baseResponseUtils.createMessage(e)
			);

		} catch (BaseEntityNotFoundException e) {
			baseResponseUtils.sendResponse(
				cachedHttpServletResponse,
				HttpStatus.OK,
				baseResponseUtils.createMessage(e)
			);
		} catch (BaseDefaultException e) {

			baseResponseUtils.sendResponse(
				cachedHttpServletResponse,
				HttpStatus.INTERNAL_SERVER_ERROR,
				baseResponseUtils.createMessage(e)
			);

		} catch (ConstraintViolationException e) {

			baseResponseUtils.sendResponse(
				cachedHttpServletResponse,
				HttpStatus.BAD_REQUEST,
				baseResponseUtils.createMessage(e)
			);

		} catch (Exception e) {
			e.printStackTrace();

			baseResponseUtils.sendResponse(
				cachedHttpServletResponse,
				HttpStatus.INTERNAL_SERVER_ERROR,
				baseResponseUtils.createMessage(new BaseDefaultException(
					BaseCode.GLOBAL_DEFAULT_FAIL_CODE,
					e
				))
			);

		} finally {
			if (!cachedHttpServletResponse.getContentType()
				.contains("html")) {
				log.info(
					"RESPONSE DATA: {}",
					cachedHttpServletResponse.getContentString()
				);
			}
			cachedHttpServletResponse.copyBodyToResponse();
			MDC.clear();
		}
	}

}
