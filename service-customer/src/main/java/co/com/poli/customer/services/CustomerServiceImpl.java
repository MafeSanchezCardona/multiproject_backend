package co.com.poli.customer.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.poli.customer.entities.Customer;
import co.com.poli.customer.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService
{
	private final CustomerRepository customerRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(final Customer customer)
	{
		customerRepository.save(customer);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(final Customer customer)
	{
		customerRepository.delete(customer);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll()
	{
		return customerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findById(final Long id)
	{
		return customerRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findByNumberId(final String numberId)
	{
		return customerRepository.findByNumberID(numberId);
	}
}
