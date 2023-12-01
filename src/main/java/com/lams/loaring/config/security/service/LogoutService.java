package com.lams.loaring.config.security.service;

import com.lams.loaring.config.security.token.domain.LogoutToken;

public interface LogoutService {

	LogoutToken save(String email);
}
