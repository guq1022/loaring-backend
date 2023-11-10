package com.lams.loaring.sample;


import com.lams.loaring.config.DataJpaConfiguration;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;


@DataJpaTest
@Import(DataJpaConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SampleTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void 엔티티_메니저_테스트(){
        Assertions.assertThat(entityManager).isNotNull();
    }

}
