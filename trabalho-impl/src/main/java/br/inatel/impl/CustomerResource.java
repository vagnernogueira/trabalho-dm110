package br.inatel.impl;

import java.util.List;

import br.inatel.dm110.dto.CustomerDTO;
import br.inatel.dm110.interfaces.CustomerBeanLocal;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource
{
	@EJB
	private CustomerBeanLocal customerBean;

	@POST
	public Response createCustomer(CustomerDTO dto)
	{
		try
		{
			customerBean.create(dto);
			return Response.status(Response.Status.CREATED).build(); // 201 Created
		}
		catch (Exception e)
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar cliente.").build(); // 500 Internal Server Error
		}
	}

	@GET
	@Path("/{cpf}")
	public Response getCustomer(@PathParam("cpf")
	String cpf)
	{
		try
		{
			CustomerDTO dto = customerBean.findByCpf(cpf);
			if (dto != null)
			{
				return Response.ok(dto).build(); // 200 OK
			}
			return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado.").build(); // 404 Not Found
		}
		catch (Exception e)
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar cliente.").build(); // 500 Internal Server Error
		}
	}

	@GET
	public Response listCustomers()
	{
		try
		{
			List<CustomerDTO> customers = customerBean.findAll();
			return Response.ok(customers).build(); // 200 OK
		}
		catch (Exception e)
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar clientes.").build(); // 500 Internal Server Error
		}
	}

	@PUT
	@Path("/{cpf}")
	public Response updateCustomer(@PathParam("cpf")
	String cpf, CustomerDTO dto)
	{
		try
		{
			boolean updated = customerBean.updateCustomer(cpf, dto);
			if (updated)
			{
				return Response.ok().build(); // 200 OK
			}
			return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado para atualização.").build(); // 404 Not Found
		}
		catch (Exception e)
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar cliente.").build(); // 500 Internal Server Error
		}
	}
}
