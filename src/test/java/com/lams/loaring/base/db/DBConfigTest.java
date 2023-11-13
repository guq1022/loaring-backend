package com.lams.loaring.base.db;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class DBConfigTest extends DBSession {

	@Test
	void 체크() {
		Assertions.assertThat(entityManager).isNotNull();
		Assertions.assertThat(jpaQueryFactory).isNotNull();
	}
}
