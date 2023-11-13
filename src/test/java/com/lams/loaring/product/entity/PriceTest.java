package com.lams.loaring.product.entity;

import com.lams.loaring.product.entity.Price;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PriceTest {

    @Test
    void 가격생성() {
        var price = new Price(2_000L);
        Assertions.assertThat(price).isNotNull();
    }

    @Test
    void 가격_생성시_정수가_아닌_경우_예외() {
        Assertions.assertThatThrownBy(() -> {
            new Price("abc");
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정수가 아닙니다.");
    }

}
