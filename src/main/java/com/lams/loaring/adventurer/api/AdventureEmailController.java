package com.lams.loaring.adventurer.api;

import com.lams.loaring.adventurer.application.EmailCheckService;
import com.lams.loaring.adventurer.dto.AdventurerEmailRequest;
import com.lams.loaring.adventurer.dto.AdventurerResponse;
import com.lams.loaring.config.dto.BaseResponse;
import com.lams.loaring.config.dto.BaseResponse.Headers;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * INSERT INTO ADVENTURER
 * VALUES(2L,'GENERAL','user1@gmail.com','$2a$10$yxmlE1fKRduWxvTqrThx4OByZeY9ZUxdTRoloNn262TkcfAbotyke','NONE')
 * INSERT INTO ADVENTURE_AUTHORITY VALUES(2L, 1L, 'USER');
 */
@RestController
@RequestMapping("/api/adventurers")
@RequiredArgsConstructor
public class AdventureEmailController {

	private final EmailCheckService emailCheckService;

	@GetMapping
	public ResponseEntity<BaseResponse> isAlreadyRegister(
		@Valid AdventurerEmailRequest adventurerEmailRequest
	) {

		Headers headers = Headers.builder()
			.code("000")
			.message("정상 처리되었습니다.")
			.build();

		if ("verify".equals(adventurerEmailRequest.getType())) {
			var adventurer = emailCheckService.existsAdventurer(adventurerEmailRequest.getEmail());

			BaseResponse baseResponse = BaseResponse.builder()
				.headers(headers)
				.data(adventurer)
				.build();
			return ResponseEntity.ok(baseResponse);
		}

		var adventurer = emailCheckService.findAdventurer(adventurerEmailRequest.getEmail());

		var baseResponse = BaseResponse.builder()
			.headers(headers)
			.data(AdventurerResponse.builder()
				.email(adventurer.getEmail())
				.password(null)
				.account(adventurer.getAccount())
				.build())
			.build();

		return ResponseEntity.ok(baseResponse);
	}


	@GetMapping("/denied")
	public ResponseEntity<String> getAdmin() {
		return ResponseEntity.ok("admin");
	}

}
