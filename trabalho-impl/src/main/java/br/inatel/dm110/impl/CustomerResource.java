package br.inatel.dm110.impl;

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
	@Path("/create")
	public Response createCustomer(CustomerDTO dto)
	{
		try
		{
			customerBean.create(dto);
			return Response.status(Response.Status.CREATED).entity("Customer successfully created.").build();
		}
		catch (Exception e)
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error while creating customer.").build();
		}
	}

	@GET
	@Path("/get/{cpf}")
	public Response getCustomer(@PathParam("cpf")
	String cpf)
	{
		try
		{
			CustomerDTO dto = customerBean.findByCpf(cpf);
			if (dto != null)
			{
				return Response.ok(dto).build();
			}
			return Response.status(Response.Status.NOT_FOUND).entity("Customer not found.").build();
		}
		catch (Exception e)
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error while retrieving customer.").build();
		}
	}

	@GET
	@Path("/list")
	public Response listCustomers()
	{
		try
		{
			List<CustomerDTO> customers = customerBean.findAll();
			return Response.ok(customers).build();
		}
		catch (Exception e)
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error while listing customers.").build();
		}
	}

	@PUT
	@Path("/update/{cpf}")
	public Response updateCustomer(@PathParam("cpf")
	String cpf, CustomerDTO dto)
	{
		try
		{
			boolean updated = customerBean.updateCustomer(cpf, dto);
			if (updated)
			{
				return Response.ok("Customer successfully updated.").build();
			}
			return Response.status(Response.Status.NOT_FOUND).entity("Customer not found for update.").build();
		}
		catch (Exception e)
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error while updating customer.").build();
		}
	}
}
