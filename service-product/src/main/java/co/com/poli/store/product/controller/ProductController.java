package co.com.poli.store.product.controller;

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

import co.com.poli.store.product.entities.Product;
import co.com.poli.store.product.services.ProductService;
import co.com.poli.store.product.utils.ErrorMessage;
import co.com.poli.store.product.utils.Response;
import co.com.poli.store.product.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController
{
	private final ProductService productService;

	private final ResponseBuilder builder;

	@PostMapping
	public Response save(@Valid @RequestBody Product product, BindingResult result)
	{
		if (result.hasErrors())
		{
			return builder.failed(this.formatMessage(result));
		}

		productService.save(product);

		return builder.success(product);
	}

	@DeleteMapping("/{id}")
	public Response delete(@PathVariable("id") Long id)
	{
		Product product = productService.findById(id);

		if (Objects.isNull(product))
		{
			return builder.notFound();
		}

		productService.delete(product);
		return builder.success(product);
	}

	@GetMapping
	public Response findAll()
	{
		List<Product> products = productService.findAll();

		if (products.isEmpty())
		{
			return builder.noContent();
		}
		return builder.success(products);
	}

	@GetMapping("/{id}")
	public Response findById(@PathVariable("id") Long id)
	{
		Product product = productService.findById(id);

		if (Objects.isNull(product))
		{
			return builder.noContent();
		}

		return builder.success(product);
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
