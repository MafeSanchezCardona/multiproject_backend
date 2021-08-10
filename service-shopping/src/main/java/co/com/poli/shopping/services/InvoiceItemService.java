package co.com.poli.shopping.services;

import java.util.List;

import co.com.poli.shopping.entities.InvoiceItem;


public interface InvoiceItemService
{
	void save(InvoiceItem invoiceItem);

	void delete(InvoiceItem invoiceItem);

	List<InvoiceItem> findAll();

	InvoiceItem findById(Long id);
}
