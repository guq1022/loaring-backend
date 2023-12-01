package com.lams.loaring.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lams.loaring.adventurer.application.AdventurerRefreshServiceImpl;
import com.lams.loaring.adventurer.application.AdventurerServiceImpl;
import com.lams.loaring.adventurer.infra.AdventurerRepository;
import com.lams.loaring.adventurer.infra.RefreshRepository;
import com.lams.loaring.config.dto.BaseResponseUtils;
import com.lams.loaring.config.exception.BaseBusinessException;
import com.lams.loaring.config.message.BaseCode;
import com.lams.loaring.config.security.filter.JwtTokenAuthenticationFilter;
import com.lams.loaring.config.security.filter.LoginAuthenticationFilter;
import com.lams.loaring.config.security.handler.JwtAuthenticationTokenFailure;
import com.lams.loaring.config.security.handler.JwtAuthenticationTokenSuccess;
import com.lams.loaring.config.security.password.BCryptPasswordEncoderAdapter;
import com.lams.loaring.config.security.password.PasswordEncoderHelper;
import com.lams.loaring.config.security.provider.JwtAuthenticationProvider;
import com.lams.loaring.config.security.service.AdventurerService;
import com.lams.loaring.config.security.service.RefreshService;
import com.lams.loaring.config.security.token.JwtTokenConfig;
import com.lams.loaring.config.security.token.JwtTokenProvider;
import com.lams.loaring.config.security.token.JwtTokenUtils;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Slf4j
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final ObjectMapper objectMapper;
	private final Validator validator;
	private final BaseResponseUtils baseResponseUtils;
	private final AdventurerRepository adventurerRepository;
	private final RefreshRepository refreshRepository;

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {

		return web -> web.ignoring()
			.requestMatchers(PathRequest.toStaticResources()
				.atCommonLocations())
			.requestMatchers("/error");
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(AbstractHttpConfigurer::disable)
			.headers(authorize -> authorize.frameOptions(Customizer.withDefaults()))
			.sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.formLogin(AbstractHttpConfigurer::disable)
			.rememberMe(AbstractHttpConfigurer::disable)
		// .headers(AbstractHttpConfigurer::disable)

		;
		// SettingAuthority
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/adventurers/denied")
			.hasRole("ADMIN")
			.requestMatchers(
				"/api/adventurers",
				"/api/adventurers/**"
			)
			.hasRole("USER")
			.requestMatchers("/api/**",
				"/api/login",
				"/docs/**"
			)
			.permitAll()
			.requestMatchers(PathRequest.toH2Console())
			.permitAll()

		);

		http.addFilterBefore(
			loginAuthenticationFilter(),
			UsernamePasswordAuthenticationFilter.class
		);
		http.addFilterBefore(
			jwtTokenAuthenticationFilter(),
			LoginAuthenticationFilter.class
		);

		http.exceptionHandling(handlingConfigurer -> {
			handlingConfigurer.accessDeniedHandler(authenticationAccessDeniedHandler());
			handlingConfigurer.authenticationEntryPoint(authenticationEntryPoint());
		});

		return http.build();
	}

	@Bean
	public LoginAuthenticationFilter loginAuthenticationFilter() {
		var jwtAuthenticationFilter = new LoginAuthenticationFilter(
			objectMapper,
			validator,
			new AntPathRequestMatcher(
				"/api/login",
				HttpMethod.POST.name()
			)
		);

		jwtAuthenticationFilter.setAuthenticationManager(authenticationManager());
		jwtAuthenticationFilter.setAuthenticationSuccessHandler(successHandler());
		jwtAuthenticationFilter.setAuthenticationFailureHandler(failureHandler());

		return jwtAuthenticationFilter;
	}

	@Bean
	public JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter() {
		return new JwtTokenAuthenticationFilter(
			adventurerService(),
			jwtTokenUtils()
		);
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(jwtAuthenticationProvider());
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new JwtAuthenticationTokenSuccess(baseResponseUtils,
			jwtTokenUtils(),
			adventurerService()
		);
	}

	@Bean
	public JwtTokenProvider jwtTokenProvider() {
		return new JwtTokenProvider(jwtTokenConfig());
	}

	@Bean
	public JwtTokenUtils jwtTokenUtils() {
		return new JwtTokenUtils(
			jwtTokenConfig(),
			jwtTokenProvider()
		);
	}

	@Bean
	public JwtTokenConfig jwtTokenConfig() {
		return new JwtTokenConfig(
			1L,
			14L,
			7L,
			"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
		);
	}

	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new JwtAuthenticationTokenFailure();
	}

	@Bean
	public AccessDeniedHandler authenticationAccessDeniedHandler() {
		return (request, response, accessDeniedException) -> {
			throw new BaseBusinessException(
				BaseCode.SECURITY_FAIL_DENIED_CODE,
				accessDeniedException
			);
		};
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return (request, response, authException) -> {
			throw new BaseBusinessException(
				BaseCode.SECURITY_FAIL_UNAUTHORIZED_CODE,
				authException
			);
		};
	}

	@Bean
	public PasswordEncoderHelper passwordEncoder() {
		return new BCryptPasswordEncoderAdapter();
	}

	@Bean
	public JwtAuthenticationProvider jwtAuthenticationProvider() {
		return new JwtAuthenticationProvider(adventurerService());
	}

	@Bean
	public AdventurerService adventurerService() {

		return new AdventurerServiceImpl(
			adventurerRepository,
			passwordEncoder(),
			refreshService(),
			jwtTokenUtils()
		);

	}

	@Bean
	public RefreshService refreshService() {
		return new AdventurerRefreshServiceImpl(refreshRepository);
	}

}
