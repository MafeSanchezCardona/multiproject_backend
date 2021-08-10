package co.com.poli.shopping.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.poli.shopping.entities.InvoiceItem;
import co.com.poli.shopping.repositories.InvoiceItemRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class InvoiceItemServiceImpl implements InvoiceItemService
{
	private final InvoiceItemRepository invoiceItemRepository;

	@Override
	public void save(final InvoiceItem invoiceItem)
	{
		invoiceItemRepository.save(invoiceItem);
	}

	@Override
	public void delete(final InvoiceItem invoiceItem)
	{
		invoiceItemRepository.delete(invoiceItem);
	}

	@Override
	public List<InvoiceItem> findAll()
	{
		return invoiceItemRepository.findAll();
	}

	@Override
	public InvoiceItem findById(final Long id)
	{
		return invoiceItemRepository.findById(id).orElse(null);
	}
}
