package com.lams.loaring.adventurer.application;

import com.lams.loaring.adventurer.domain.Adventurer;
import com.lams.loaring.adventurer.domain.Email;
import com.lams.loaring.adventurer.infra.AdventurerRepository;
import com.lams.loaring.config.exception.BaseEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdventurerQueryService {

	private final AdventurerRepository adventurerRepository;

	public Adventurer findAdventurer(String email) {
		return adventurerRepository.findByEmail(Email.of(email))
			.orElseThrow(() -> new BaseEntityNotFoundException("모험가"));
	}

	public boolean existsAdventurer(String email) {
		return adventurerRepository.existsByEmail(Email.of(email));
	}

}
