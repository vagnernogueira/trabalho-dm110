package br.inatel.dm110.beans;

import java.util.List;

import br.inatel.dm110.dto.CustomerDTO;
import br.inatel.dm110.entities.Customer;
import br.inatel.dm110.interfaces.CustomerBeanLocal;
import br.inatel.dm110.support.CustomerConverter;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class CustomerBean implements CustomerBeanLocal
{
	@PersistenceContext(unitName = "trabalho_dm110_pu")
	private EntityManager em;

	public void create(CustomerDTO customerDTO)
	{
		Customer customer = CustomerConverter.toEntity(customerDTO);
		em.persist(customer);
	}

	public CustomerDTO findByCpf(String cpf)
	{
		Customer customer = em.find(Customer.class, cpf);
		return CustomerConverter.toDTO(customer);
	}

	public List<CustomerDTO> findAll()
	{
		List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
		return CustomerConverter.toDTOList(customers);
	}

	public boolean updateCustomer(String cpf, CustomerDTO dto)
	{
		Customer customer = em.find(Customer.class, cpf);
		if (customer != null)
		{
			customer.setName(dto.getName());
			customer.setBirthdate(dto.getBirthdate());
			customer.setGender(dto.getGender());
			customer.setEmail(dto.getEmail());
			customer.setCep(dto.getCep());
			return true;
		}
		return false;
	}
}
