package com.lams.loaring.sample.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import com.lams.loaring.config.application.BaseServiceHelper;
import com.lams.loaring.sample.domain.DefaultType;
import com.lams.loaring.sample.domain.Sample;
import com.lams.loaring.sample.domain.SampleRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class SampleServiceTest extends BaseServiceHelper {

	@Mock
	private SampleRepository sampleRepository;

	@InjectMocks
	private SampleService sampleService;

	@Test
	void getSamples() {

		// given
		var givenSamples = List.of(Sample.builder()
				.id(1L)
				.defaultType(DefaultType.GENERAL)
				.build(),
			Sample.builder()
				.id(1L)
				.defaultType(DefaultType.GENERAL)
				.build());

		doReturn(givenSamples)
			.when(sampleRepository)
			.findAll();

		// when
		var samples = sampleService.getSamples();

		// then
		assertThat(givenSamples).containsAll(samples);
	}
}