package com.lams.loaring.product.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long qty;

    @Embedded
    private Price price;

    public Product(Long qty, String price) {
        this.qty = qty;
        this.price = new Price(price);
    }

    @Builder
    public Product(Long id, Long qty, String price) {
        this.id = id;
        this.qty = qty;
        this.price = new Price(price);
    }

    public static Product create(Long qty, String price) {
        return new Product(qty, price);
    }

    public void modifyPrice(String price) {
        this.price = new Price(price);
    }

    @Deprecated
    public void modifyPrice(BigDecimal price) {
        this.price = new Price(price);
    }
}
