package co.com.poli.shopping.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.poli.shopping.entities.Invoice;
import co.com.poli.shopping.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService
{
	private final InvoiceRepository invoiceRepository;

	@Override
	public void save(final Invoice invoice)
	{
		invoiceRepository.save(invoice);
	}

	@Override
	public void delete(final Invoice invoice)
	{
		invoiceRepository.delete(invoice);
	}

	@Override
	public List<Invoice> findAll()
	{
		return invoiceRepository.findAll();
	}

	@Override
	public Invoice findById(final Long id)
	{
		return invoiceRepository.findById(id).orElse(null);
	}

	@Override
	public List<Invoice> findByCustomerId(final Long customerId)
	{
		return invoiceRepository.findByCustomerId(customerId);
	}

	@Override
	public Invoice findByNumber(final String number)
	{
		return invoiceRepository.findByNumber(number);
	}
}
