package com.lams.loaring.product.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.regex.Pattern;

@Getter
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class Price {

    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    private BigDecimal price;

    public Price(BigDecimal price) {
        this.price = price;
    }

    public Price(Long price) {
        this.price = new BigDecimal(price);
    }

    public Price(String price) {
        if(isNumeric(price)) {
            throw new IllegalArgumentException("정수가 아닙니다.");
        }
        this.price = new BigDecimal(price);
    }

    public Price add(String price) {
        this.price.add(new BigDecimal(price));

        return new Price(this.price);
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return !pattern.matcher(strNum).matches();
    }

}
