package br.inatel.dm110.interfaces;

import java.util.List;

import br.inatel.dm110.dto.CustomerDTO;

public interface CustomerBeanLocal
{
	public void create(CustomerDTO customerDTO);

	public CustomerDTO findByCpf(String cpf);

	public List<CustomerDTO> findAll();

	public boolean updateCustomer(String cpf, CustomerDTO dto);
}
