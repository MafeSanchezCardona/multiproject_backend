package co.com.poli.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.poli.customer.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>
{
	Customer findByNumberID(String numberID);
}
