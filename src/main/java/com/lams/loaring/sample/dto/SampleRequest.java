package com.lams.loaring.sample.dto;

import com.lams.loaring.config.valid.ValidEnum;
import com.lams.loaring.sample.domain.DefaultType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SampleRequest {

	@NotEmpty
	private String name;

	@ValidEnum(enumClass = DefaultType.class)
	private DefaultType defaultType;

	@NotNull
	List<String> abc;

}
