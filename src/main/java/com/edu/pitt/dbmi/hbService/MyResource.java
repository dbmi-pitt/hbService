package com.edu.pitt.dbmi.hbService;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.edu.pitt.dbmi.hbService.models.Person;
import com.edu.pitt.dbmi.hbService.services.PersonService;

@Path("myresource")
public class MyResource {
PersonService personService = new PersonService();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllPersons() {
		return "Ok";
	}
}
