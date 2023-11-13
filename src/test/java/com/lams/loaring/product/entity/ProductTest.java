package com.lams.loaring.product.entity;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;

/**
 * 사용자가 상품을 등록할 수 있게 해주세요.
 * 상품은 수량, 단가를 입력할 수 있어야한다.
 * 수량을 수정하고 싶습니다.
 * 단가에 문자열이 등록되지 않도록 해주세요.
 */
@SpringBootTest
@Transactional
@Rollback(value = false)
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void 상품_등록() {
        Product product = new Product(1L, new BigDecimal("1000"));
        productRepository.save(product);
    }

    @Test
    void 상품_등록_빌더패턴() {
        Product product = Product.builder()
                .id(1L)
                .price(new BigDecimal("1000"))
                .build();
        productRepository.save(product);
    }

    @Test
    void 상품_등록_스태틱_메소드_패턴() {
        Product product = Product.create(1L, new BigDecimal("1000"));
        productRepository.save(product);
    }

    @Test
    void 상품_단가_수정() {
        Product product = new Product(1L, new BigDecimal("1000"));
        productRepository.save(product);

        Product findProduct = productRepository.findById(1L).orElseThrow();
        findProduct.modifyPrice(new BigDecimal("2000"));

        Product updateProduct = productRepository.findById(1L).orElseThrow();
        Assertions.assertThat(updateProduct.getPrice()).isEqualTo(new Price(2_000L));

    }

}
