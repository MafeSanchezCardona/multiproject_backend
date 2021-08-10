package co.com.poli.customer.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="customers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false,nullable = false,unique = true)
	private Long id;
	@NotEmpty(message = "El nit/numero no debe ser vacio")
	@Size(min = 8, message = "El tamaño del nit/numero debe ser minimo 8")
	@Column(name="number_id", unique = true, nullable = false)
	private String numberID;
	@NotEmpty(message = "El nombre no debe ser vacio")
	@Column(name="first_name")
	private String firstName;
	@NotEmpty(message = "El apellido no debe ser vacio")
	@Column(name="last_name")
	private String lastName;
	@Email(message = "NO es una direccion de email valida")
	@Column(name="email", unique = true, nullable = false)
	private String email;

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Customer customer = (Customer) o;
		return Objects.equals(id, customer.id);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}
}
