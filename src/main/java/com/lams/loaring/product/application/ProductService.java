package com.lams.loaring.product.application;

import com.lams.loaring.product.entity.Product;
import com.lams.loaring.product.entity.ProductRepository;
import com.lams.loaring.product.infra.QProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final QProductRepository qProductRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product modifyProduct(Long id, String price) {

        var findProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("데이터가 존재하지 않습니다."));

        findProduct.modifyPrice(new BigDecimal(price));

        return findProduct;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getQProducts() {
        return qProductRepository.findAll();
    }

}
