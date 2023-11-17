package com.lams.loaring.sample.application;

import com.lams.loaring.sample.domain.Sample;
import com.lams.loaring.sample.domain.SampleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService {

	private final SampleRepository sampleRepository;

	public List<Sample> getSamples() {
		return sampleRepository.findAll();
	}

}
