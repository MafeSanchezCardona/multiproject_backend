package co.com.poli.store.product;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.poli.store.product.entities.Category;
import co.com.poli.store.product.entities.Product;
import co.com.poli.store.product.repositories.ProductRepository;
import co.com.poli.store.product.services.ProductService;
import co.com.poli.store.product.services.ProductServiceImpl;


@SpringBootTest
public class ProductServiceMockTest
{
	@Mock
	private ProductRepository productRepository;
	private ProductService productService;

	@BeforeEach
	public void begin()
	{
		MockitoAnnotations.initMocks(this);
		productService = new ProductServiceImpl(productRepository);

		Category category = Category.builder().id(1L).build();

		Product product = Product.builder().id(4L).name("Test").stock(10D).price(1000D).category(category).build();

		Mockito.when(productRepository.findById(4L)).thenReturn(Optional.of(product));
	}

	@Test
	public void when_findById_return_product(){
		Product product = productService.findById(4L);
		Assertions.assertThat(product.getName()).isEqualTo("Test");
	}
}
