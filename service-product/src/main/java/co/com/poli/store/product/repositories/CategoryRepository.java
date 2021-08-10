package co.com.poli.store.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.poli.store.product.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>
{
}
