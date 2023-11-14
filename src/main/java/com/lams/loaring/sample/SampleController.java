package com.lams.loaring.sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SampleController {

	@PostMapping("/api")
	public ResponseEntity<String> getApi(@RequestBody String name) {
		return ResponseEntity.ok(name);
	}


	@PostMapping("/api/test")
	public ResponseEntity<MultipartFile> getApi(@RequestParam MultipartFile file) {
		return ResponseEntity.ok(file);
	}
}
