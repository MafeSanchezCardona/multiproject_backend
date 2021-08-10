package co.com.poli.store.product.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.poli.store.product.entities.Product;
import co.com.poli.store.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService
{
	private final ProductRepository productRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(final Product product)
	{
		productRepository.save(product);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(final Product product)
	{
		productRepository.delete(product);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll()
	{
		return productRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(final Long id)
	{
		return productRepository.findById(id).orElse(null);
	}
}
