package com.lams.loaring.sample.domain;

import com.lams.loaring.config.BaseEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DefaultType implements BaseEnum {

	GENERAL("general", "일반"), NONE("none", "없음");

	private final String code;
	private final String comment;

	@Override
	public String getKey() {
		return name();
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Converter(autoApply = true)
	static class DefaultTypeConverter implements AttributeConverter<DefaultType, String> {

		@Override
		public String convertToDatabaseColumn(DefaultType attribute) {
			return DefaultType.NONE.getCode();
		}

		@Override
		public DefaultType convertToEntityAttribute(String dbData) {
			return DefaultType.valueOf(dbData.toUpperCase());
		}
	}

}
