package br.inatel.dm110.support;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.dm110.dto.CustomerDTO;
import br.inatel.dm110.entities.Customer;

public class CustomerConverter
{
	public static CustomerDTO toDTO(Customer entity)
	{
		if (entity == null)
		{
			return null;
		}
		return new CustomerDTO(entity.getCpf(), entity.getName(), entity.getBirthdate(), entity.getGender(), entity.getEmail(), entity.getCep());
	}

	public static Customer toEntity(CustomerDTO dto)
	{
		return new Customer(dto.getCpf(), dto.getName(), dto.getBirthdate(), dto.getGender(), dto.getEmail(), dto.getCep());
	}

	public static List<CustomerDTO> toDTOList(List<Customer> entityList)
	{
		return entityList.stream().map(CustomerConverter::toDTO).collect(Collectors.toList());
	}

	public static List<Customer> toEntityList(List<CustomerDTO> dtoList)
	{
		return dtoList.stream().map(CustomerConverter::toEntity).collect(Collectors.toList());
	}
}
