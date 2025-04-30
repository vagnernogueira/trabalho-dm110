package br.inatel.dm110.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTO implements Serializable
{
	private static final long serialVersionUID = 1072605213898958718L;

	private String cpf;

	private String name;

	private LocalDate birthdate;

	private String gender;

	private String email;

	private String cep;
}
