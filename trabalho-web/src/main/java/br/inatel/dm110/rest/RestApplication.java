package br.inatel.dm110.rest;

import java.util.HashSet;
import java.util.Set;

import br.inatel.impl.CustomerResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application
{
	@Override
	public Set<Class<?>> getClasses()
	{
		Set<Class<?>> classes = new HashSet<>();
		// register the classes to publish the rest service
		classes.add(CustomerResource.class);
		return classes;
	}
}
