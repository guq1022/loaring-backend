package com.lams.loaring.sample.api;

import com.lams.loaring.sample.application.SampleService;
import com.lams.loaring.sample.dto.SampleRequest;
import com.lams.loaring.sample.dto.SampleResponse;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/samples")
@RequiredArgsConstructor
public class SampleQueryController {

	private final SampleService sampleService;

	@GetMapping
	public ResponseEntity<List<SampleResponse>> getSamples(SampleRequest sampleRequest) {
		var samples = sampleService.getSamples();

		return ResponseEntity.ok(SampleResponse.ofList(samples));
	}

	@PostMapping
	public ResponseEntity<List<SampleResponse>> saveSample(
		@Valid @RequestBody SampleRequest sampleRequest) {

		return ResponseEntity.ok(Collections.emptyList());
	}


	@GetMapping("/{id}")
	public ResponseEntity<SampleResponse> getSample(@PathVariable Long id) {
		return ResponseEntity.ok(new SampleResponse());
	}


}
