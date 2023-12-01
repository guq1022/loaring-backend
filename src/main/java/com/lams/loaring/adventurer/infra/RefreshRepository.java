package com.lams.loaring.adventurer.infra;

import com.lams.loaring.config.security.token.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshRepository extends CrudRepository<RefreshToken, String> {
	
}
