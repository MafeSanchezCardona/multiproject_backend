package co.com.poli.shopping.services;

import java.util.List;

import co.com.poli.shopping.entities.Invoice;


public interface InvoiceService
{
	void save(Invoice invoice);

	void delete(Invoice invoice);

	List<Invoice> findAll();

	Invoice findById(Long id);

	List<Invoice> findByCustomerId(Long customerId);

	Invoice findByNumber(String number);
}

