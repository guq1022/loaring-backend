package com.lams.loaring.product.application;

import com.lams.loaring.config.application.BaseServiceTest;
import com.lams.loaring.product.entity.Product;
import com.lams.loaring.product.entity.ProductRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

class ProductServiceTest extends BaseServiceTest {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	@Test
	void getProducts() {
		// given
		var 피자 = Product.builder()
			.id(1L)
			.qty(1L)
			.price("1000")
			.build();

		var 채소 = Product.builder()
			.id(1L)
			.qty(1L)
			.price("1000")
			.build();

		List<Product> 상품들 = List.of(피자, 채소);

		Mockito.doReturn(상품들).when(productRepository).findAll();

		// when
		List<Product> products = productService.getProducts();

		// then
		Assertions.assertThat(products).containsAll(상품들);

	}
}