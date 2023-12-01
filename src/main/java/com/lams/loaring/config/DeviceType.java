package com.lams.loaring.config;

import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeviceType implements BaseEnum<List<?>> {

	ANDROID(List.of(AndroidType.ANDROID), "안드로이드 OS"),
	IOS(List.of(IOSType.IPAD, IOSType.IPHONE, IOSType.IPOD), "IOS OS"),
	NONE(Collections.emptyList(), "다른 플랫폼"),
	;

	private final List<?> code;
	private final String comment;

	@Override
	public String getKey() {
		return name();
	}

	@Getter
	@AllArgsConstructor
	public enum AndroidType implements BaseEnum<String> {

		ANDROID("android", "안드로이드");

		private final String code;
		private final String comment;

		@Override
		public String getKey() {
			return name();
		}

	}

	@Getter
	@AllArgsConstructor
	public enum IOSType implements BaseEnum<String> {

		IPHONE("iphone", "아이폰"),
		IPAD("ipad", "아이패드"),
		IPOD("ipod", "아이팟");

		private final String code;
		private final String comment;

		@Override
		public String getKey() {
			return name();
		}

	}

}

