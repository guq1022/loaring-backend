package com.lams.loaring.product.infra;

import com.lams.loaring.product.entity.Product;
import com.lams.loaring.product.entity.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QProductRepositoryImpl implements QProductRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> findAll() {
        return jpaQueryFactory
                .select(QProduct.product)
                .from(QProduct.product)
                .fetch();
    }

}
