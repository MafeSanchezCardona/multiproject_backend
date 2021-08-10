package co.com.poli.shopping.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="invoice")
@Builder
public class Invoice
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false,nullable = false,unique = true)
	private Long id;
	@Column(name="number")
	private String number;
	@Column(name="description")
	private String description;
	@Column(name="customer_id")
	private Long customerId;

	@Valid
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="invoice_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<InvoiceItem> items;

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Invoice invoice = (Invoice) o;
		return Objects.equals(id, invoice.id);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}
}
