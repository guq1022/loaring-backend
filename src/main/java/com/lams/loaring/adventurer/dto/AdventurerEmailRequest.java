package com.lams.loaring.adventurer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdventurerEmailRequest {

	@Email
	@NotNull
	private String email;

	private String type = "none";

}
