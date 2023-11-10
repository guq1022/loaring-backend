package com.lams.loaring.config;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestJpaConfiguration.class)
public @interface DataBaseTest {

}
