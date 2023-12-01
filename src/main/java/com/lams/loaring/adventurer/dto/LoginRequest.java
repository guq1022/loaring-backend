package com.lams.loaring.adventurer.dto;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import com.lams.loaring.adventurer.domain.Account.SnsType;
import com.lams.loaring.config.valid.ValidEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class LoginRequest {

	@Email
	@NotEmpty
	private String email;

	@Size(min = 1, max = 18)
	@NotEmpty
	private String password;

	@ValidEnum(enumClass = SnsType.class)
	private SnsType snsType;
}
