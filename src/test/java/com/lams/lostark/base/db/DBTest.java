package com.lams.lostark.base.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Repository.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(TestJPAConfiguration.class)
public @interface DBTest {

}
