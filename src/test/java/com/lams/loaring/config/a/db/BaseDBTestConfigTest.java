package com.lams.loaring.config.a.db;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class BaseDBTestConfigTest extends BaseDBTest {

	@Test
	void datasource() {
		Assertions.assertThat(entityManager)
			.isNotNull();
		Assertions.assertThat(jpaQueryFactory)
			.isNotNull();
	}

}
