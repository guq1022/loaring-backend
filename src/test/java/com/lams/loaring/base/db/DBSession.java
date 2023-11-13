package com.lams.loaring.base.db;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;

@DBTest
public abstract class DBSession {

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	protected JPAQueryFactory jpaQueryFactory;

}
