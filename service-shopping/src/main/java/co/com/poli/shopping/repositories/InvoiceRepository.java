package co.com.poli.shopping.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.poli.shopping.entities.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long>
{
	List<Invoice> findByCustomerId(Long customerId);

	Invoice findByNumber(String number);
}
