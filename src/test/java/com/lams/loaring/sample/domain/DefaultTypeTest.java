package com.lams.loaring.sample.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DefaultTypeTest {


	@Test
	void 기본_타입() {
		DefaultType defaultType = DefaultType.valueOf("general");

		Assertions.assertEquals(defaultType, DefaultType.GENERAL);
	}

}