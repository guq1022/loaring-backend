package com.lams.loaring.config.a.db;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;

@BaseDBTestConfig
public abstract class BaseDBTest {

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	protected JPAQueryFactory jpaQueryFactory;

}
