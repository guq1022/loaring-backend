package com.lams.loaring.adventurer.application;

import com.lams.loaring.adventurer.domain.Account;
import com.lams.loaring.adventurer.domain.AdventureContext;
import com.lams.loaring.adventurer.domain.Adventurer;
import com.lams.loaring.adventurer.domain.Email;
import com.lams.loaring.adventurer.domain.Password;
import com.lams.loaring.adventurer.dto.AdventurerRegisterRequest;
import com.lams.loaring.adventurer.dto.LoginRequest;
import com.lams.loaring.adventurer.infra.AdventurerRepository;
import com.lams.loaring.config.exception.BaseBusinessException;
import com.lams.loaring.config.exception.BaseEntityNotFoundException;
import com.lams.loaring.config.message.BaseCode;
import com.lams.loaring.config.security.exception.PasswordMatchException;
import com.lams.loaring.config.security.password.PasswordEncoderHelper;
import com.lams.loaring.config.security.service.AdventurerService;
import com.lams.loaring.config.security.service.RefreshService;
import com.lams.loaring.config.security.token.JwtTokenUtils;
import com.lams.loaring.config.security.token.dto.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class AdventurerServiceImpl implements AdventurerService {

	private final AdventurerRepository adventurerRepository;
	private final PasswordEncoderHelper passwordEncoderHelper;
	private final RefreshService refreshService;
	private final JwtTokenUtils jwtTokenUtils;

	@Override
	@Transactional(readOnly = true)
	public AdventureContext findByAdventurer(String enterEmail) {

		var adventurer = adventurerRepository.findByEmail(Email.of(enterEmail))
			.orElseThrow(() -> new BaseEntityNotFoundException("모험가"));

		return new AdventureContext(adventurer);
	}

	@Transactional(readOnly = true)
	public boolean existsAdventurer(String email) {
		return adventurerRepository.existsByEmail(Email.of(email));
	}

	@Transactional
	public Long register(AdventurerRegisterRequest adventurerRegisterRequest) {
		var email = adventurerRegisterRequest.getEmail();

		if (existsAdventurer(email)) {
			throw BaseBusinessException.of(BaseCode.GLOBAL_DEFAULT_FAIL_CODE);
		}

		adventurerRegisterRequest.setPassword(passwordEncoderHelper.encode(
			adventurerRegisterRequest.getPassword()));

		var adventurer = Adventurer.builder()
			.email(Email.of(email))
			.password(Password.of(adventurerRegisterRequest.getPassword()))
			.account(Account.of(adventurerRegisterRequest.getSnsType()))
			.build();

		return adventurerRepository.save(adventurer)
			.getId();
	}

	@Transactional(readOnly = true)
	@Override
	public JwtToken login(LoginRequest loginRequest) {

		var member = authenticate(loginRequest);
		var email = member.getEmail();
		var strEmail = email.getEmail();

		var accessToken = jwtTokenUtils.createAccessToken(strEmail);

		refreshService.save(email.getEmail(), accessToken);
		return new JwtToken(accessToken, accessToken);
	}


	@Override
	@Transactional
	public void logout(String access) {

//		var email = jwtTokenUtils.parseToken(jwtTokenUtils.resolveToken(issuedAccessToken));
//		var accessToken = jwtTokenUtils.resolveToken(email);
//		var remainTime = jwtTokenUtils.getRemainTime(accessToken);
//
//		refreshService.deleteToken(email);
//		logoutService.save(LogoutToken.from(email, accessToken, remainTime));
	}

	@Override
	public JwtToken reissue(String refreshToken, String email) {
		var findRefreshToken = refreshService.findToken(email);
		return createRefreshToken(email, findRefreshToken.getRefreshToken());
	}

	private JwtToken createRefreshToken(String email, String refreshToken) {

		if (jwtTokenUtils.lessThanReissueExpirationTimesLeft(refreshToken)) {

			var accessToken = jwtTokenUtils.createAccessToken(email);
			var newRefreshToken = jwtTokenUtils.createRefreshToken(email);
			refreshService.save(email, newRefreshToken);

			return new JwtToken(accessToken, newRefreshToken);
		}

		var accessToken = jwtTokenUtils.createAccessToken(email);
		return new JwtToken(accessToken, refreshToken);

	}

	public AdventureContext authenticate(LoginRequest loginRequest) {
		var email = loginRequest.getEmail();
		var findAdventurer = findByAdventurer(email);
		verifyPassword(loginRequest.getPassword(), findAdventurer.getPassword());
		return findAdventurer;
	}

	public void verifyPassword(String enterPassword, String password) {
		if (!passwordEncoderHelper.matches(enterPassword, password)) {
			throw new PasswordMatchException();
		}
	}

	public void saveToken(String email, String refreshToken) {
		refreshService.save(email, refreshToken);
	}
}
