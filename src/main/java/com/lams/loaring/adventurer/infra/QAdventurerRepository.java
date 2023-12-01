package com.lams.loaring.adventurer.infra;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QAdventurerRepository {

	private final JPAQueryFactory jpaQueryFactory;

}