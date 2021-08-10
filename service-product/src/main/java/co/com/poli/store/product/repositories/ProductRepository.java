package co.com.poli.store.product.repositories;


import co.com.poli.store.product.entities.Category;
import co.com.poli.store.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
	List<Product> findByCategory(Category category);
}
