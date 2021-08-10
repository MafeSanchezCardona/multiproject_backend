package co.com.poli.store.product.services;

import java.util.List;

import co.com.poli.store.product.entities.Product;


public interface ProductService
{
	void save(Product product);

	void delete(Product product);

	List<Product> findAll();

	Product findById(Long id);
}
