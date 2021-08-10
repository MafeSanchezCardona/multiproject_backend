package co.com.poli.shopping.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="invoice_items")
@Builder
public class InvoiceItem
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false,nullable = false,unique = true)
	private Long id;
	@Positive(message = "La cantidad debe ser mayor que cero")
	@Column(name="quantity")
	private Double quantity;
	@Positive(message = "La cantidad debe ser mayor que cero")
	@Column(name="price")
	private Double price;
	@Column(name="product_id")
	private Long productId;

	@Transient
	private Double subTotal;

	public Double getSubtotal(){
		if (this.price > 0 && this.quantity > 0){
			return this.quantity * this.price;
		} else {
			return 0d;
		}
	}

	public InvoiceItem(){
		this.quantity = 0d;
		this.price = 0d;
	}


}
