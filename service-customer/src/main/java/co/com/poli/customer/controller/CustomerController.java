package co.com.poli.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.poli.customer.entities.Customer;
import co.com.poli.customer.services.CustomerService;
import co.com.poli.customer.utils.ErrorMessage;
import co.com.poli.customer.utils.Response;
import co.com.poli.customer.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController
{
	private final CustomerService customerService;

	private final ResponseBuilder builder;

	@PostMapping
	public Response save(@Valid @RequestBody Customer customer, BindingResult result)
	{
		if (result.hasErrors())
		{
			return builder.failed(this.formatMessage(result));
		}

		customerService.save(customer);

		return builder.success(customer);
	}

	@DeleteMapping("/{id}")
	public Response delete(@PathVariable("id") Long id)
	{
		Customer customer = customerService.findById(id);

		if (Objects.isNull(customer))
		{
			return builder.notFound();
		}

		customerService.delete(customer);
		return builder.success(customer);
	}

	@GetMapping
	public Response findAll()
	{
		List<Customer> customers = customerService.findAll();

		if (customers.isEmpty())
		{
			return builder.noContent();
		}
		return builder.success(customers);
	}

	@GetMapping("/{id}")
	public Response findById(@PathVariable("id") Long id)
	{
		Customer customer = customerService.findById(id);

		if (Objects.isNull(customer))
		{
			return builder.noContent();
		}

		return builder.success(customer);
	}

	@GetMapping("/{numberID}")
	public Response findByNumberId(@PathVariable("numberID") String numberID)
	{
		Customer customer = customerService.findByNumberId(numberID);

		if (Objects.isNull(customer))
		{
			return builder.noContent();
		}

		return builder.success(customer);
	}

	private String formatMessage(BindingResult result)
	{
		List<Map<String, String>> errors = result.getFieldErrors().stream().map(fieldError -> {
			Map<String, String> error = new HashMap<>();
			error.put(fieldError.getField(), fieldError.getDefaultMessage());
			return error;
		}).collect(Collectors.toList());

		ErrorMessage errorMessage = ErrorMessage.builder().messages(errors).code("01").build();

		ObjectMapper mapper = new ObjectMapper();
		String json = "";

		try
		{
			json = mapper.writeValueAsString(errorMessage);
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}

		return json;
	}
}
