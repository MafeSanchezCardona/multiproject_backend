package co.com.poli.shopping.controller;

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

import co.com.poli.shopping.entities.Invoice;
import co.com.poli.shopping.services.InvoiceService;
import co.com.poli.shopping.utils.ErrorMessage;
import co.com.poli.shopping.utils.Response;
import co.com.poli.shopping.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping")
public class InvoiceController
{
	private final InvoiceService invoiceService;

	private final ResponseBuilder builder;

	@PostMapping
	public Response save(@Valid @RequestBody Invoice invoice, BindingResult result)
	{
		if (result.hasErrors())
		{
			return builder.failed(this.formatMessage(result));
		}

		invoiceService.save(invoice);

		return builder.success(invoice);
	}

	@DeleteMapping("/{number}")
	public Response delete(@PathVariable("number") String number)
	{
		Invoice invoice = invoiceService.findByNumber(number);

		if (Objects.isNull(invoice))
		{
			return builder.notFound();
		}

		invoiceService.delete(invoice);
		return builder.success(invoice);
	}

	@GetMapping
	public Response findAll()
	{
		List<Invoice> invoices = invoiceService.findAll();

		if (invoices.isEmpty())
		{
			return builder.noContent();
		}
		return builder.success(invoices);
	}

	@GetMapping("/{id}")
	public Response findById(@PathVariable("id") Long id)
	{
		Invoice invoice = invoiceService.findById(id);

		if (Objects.isNull(invoice))
		{
			return builder.noContent();
		}

		return builder.success(invoice);
	}

	@GetMapping("/customerId/{customerId}")
	public Response findByCustomerId(@PathVariable("customerId") Long customerId)
	{
		List<Invoice> invoiceList = invoiceService.findByCustomerId(customerId);

		if (invoiceList.isEmpty())
		{
			return builder.noContent();
		}

		return builder.success(invoiceList);
	}

	@GetMapping("/number/{number}")
	public Response findByNumber(@PathVariable("number") String number)
	{
		Invoice invoice = invoiceService.findByNumber(number);

		if (Objects.isNull(invoice))
		{
			return builder.noContent();
		}

		return builder.success(invoice);
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
