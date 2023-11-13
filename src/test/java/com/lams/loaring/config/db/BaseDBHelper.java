package com.lams.loaring.config.db;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;

@BaseDBTest
public abstract class BaseDBHelper {

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	protected JPAQueryFactory jpaQueryFactory;

}
