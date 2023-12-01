package com.lams.loaring.adventurer.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.lams.loaring.adventurer.domain.Account;
import com.lams.loaring.adventurer.domain.Email;
import com.lams.loaring.adventurer.domain.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AdventurerResponse {

	private Long id;

	@JsonUnwrapped
	private Email email;

	@JsonUnwrapped
	private Password password;

	@JsonUnwrapped
	private Account account;

}
