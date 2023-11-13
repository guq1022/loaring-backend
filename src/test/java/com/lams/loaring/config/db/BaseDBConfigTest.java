package com.lams.loaring.config.db;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class BaseDBConfigTest extends BaseDBHelper {

	@Test
	void 체크() {
		Assertions.assertThat(entityManager).isNotNull();
		Assertions.assertThat(jpaQueryFactory).isNotNull();
	}
}
