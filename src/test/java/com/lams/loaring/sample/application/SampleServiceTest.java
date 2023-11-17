package com.lams.loaring.sample.application;

import static org.mockito.Mockito.doReturn;

import com.lams.loaring.config.application.BaseServiceTest;
import com.lams.loaring.sample.domain.DefaultType;
import com.lams.loaring.sample.domain.Sample;
import com.lams.loaring.sample.domain.SampleRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class SampleServiceTest extends BaseServiceTest {

	@Mock
	private SampleRepository sampleRepository;

	@InjectMocks
	private SampleService sampleService;

	@Test
	void getSamples() {

		List<Sample> all = doReturn(
			List.of(Sample.builder()
			              .id(1L)
			              .defaultType(DefaultType.GENERAL)
			              .build(),
				Sample.builder()
				      .id(1L)
				      .defaultType(DefaultType.GENERAL)
				      .build())
		).when(sampleRepository)
		 .findAll();

		List<Sample> samples = sampleService.getSamples();

		Assertions.assertThat(all)
		          .containsAll(samples);
	}
}