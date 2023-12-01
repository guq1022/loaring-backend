package com.lams.loaring.config.a.db;

import com.lams.loaring.config.db.P6spySqlFormatConfiguration;
import com.p6spy.engine.spy.P6SpyOptions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class BaseTestDBConfiguration {

	@PersistenceContext
	private EntityManager entityManager;

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}


	@PostConstruct
	public void setLogMessageFormat() {
		P6SpyOptions.getActiveInstance()
			.setLogMessageFormat(P6spySqlFormatConfiguration.class.getName());
	}
}
