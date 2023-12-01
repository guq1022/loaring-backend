package com.lams.loaring.config.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lams.loaring.adventurer.domain.Account;
import com.lams.loaring.adventurer.dto.AdventurerResponse;
import com.lams.loaring.config.BaseConfiguration;
import org.junit.jupiter.api.Test;

class BaseMapperUtilsTest {

	@Test
	void name() throws JsonProcessingException {
		BaseConfiguration baseConfiguration = new BaseConfiguration();
		ObjectMapper objectMapper = baseConfiguration.objectMapper();

		AdventurerResponse build = AdventurerResponse.builder()
			.account(Account.Default())
			.build();

		String s = objectMapper.writeValueAsString(build);

		System.out.println("s = " + s);
	}

}