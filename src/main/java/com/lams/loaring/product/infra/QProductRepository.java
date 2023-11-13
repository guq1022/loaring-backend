package com.lams.loaring.product.infra;

import com.lams.loaring.product.entity.Product;

import java.util.List;

public interface QProductRepository {

    List<Product> findAll();

}
