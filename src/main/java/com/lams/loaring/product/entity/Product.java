package com.lams.loaring.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long qty;

    @Embedded
    private Price price;

    public Product(Long qty, BigDecimal price) {
        this.qty = qty;
        this.price = new Price(price);
    }

    @Builder
    public Product(Long id, Long qty, BigDecimal price) {
        this.id = id;
        this.qty = qty;
        this.price = new Price(price);
    }

    public static Product create(Long qty, BigDecimal bigDecimal) {
        return new Product(qty, bigDecimal);
    }

    public void modifyPrice(BigDecimal price) {
        this.price = new Price(price);
    }
}
