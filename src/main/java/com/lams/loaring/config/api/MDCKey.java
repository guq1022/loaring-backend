package com.lams.loaring.config.api;

import lombok.Getter;

public enum MDCKey {
	TRX_ID("trxId"), API_CODE("apiCode");

	@Getter
	private String key;

	MDCKey(String key) {
		this.key = key;
	}
}