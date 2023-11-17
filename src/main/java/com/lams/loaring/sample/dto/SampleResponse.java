package com.lams.loaring.sample.dto;

import com.lams.loaring.sample.domain.DefaultType;
import com.lams.loaring.sample.domain.Sample;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SampleResponse {

	private Long id;

	private String name;

	private DefaultType defaultType;

	public SampleResponse(Sample sample) {
		this(sample.getId(), sample.getName(), sample.getDefaultType());
	}

	public static SampleResponse of(Sample sample) {
		return new SampleResponse(sample);
	}

	public static List<SampleResponse> ofList(List<Sample> samples) {
		return samples.stream()
		              .map(SampleResponse::of)
		              .toList();
	}
}
