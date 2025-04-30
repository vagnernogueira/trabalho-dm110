package br.inatel.dm110.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer
{
	@Id
	@Column(length = 11, nullable = false, unique = true)
	private String cpf;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(nullable = false)
	private LocalDate birthdate;

	@Column(length = 1, nullable = false)
	private String gender; // 'M' ou 'F'

	@Column(length = 100, nullable = false, unique = true)
	private String email;

	@Column(length = 8, nullable = false)
	private String cep;
}
