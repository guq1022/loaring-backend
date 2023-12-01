package com.lams.loaring.adventurer.application;

import com.lams.loaring.adventurer.domain.Adventurer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailCheckService {

	private final AdventurerQueryService adventurerQueryService;

	public Adventurer findAdventurer(String email) {
		return adventurerQueryService.findAdventurer(email);
	}

	public boolean existsAdventurer(String email) {
		return adventurerQueryService.existsAdventurer(email);
	}

}
