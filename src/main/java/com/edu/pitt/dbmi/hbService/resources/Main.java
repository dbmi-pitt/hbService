package com.edu.pitt.dbmi.hbService.resources;

import com.edu.pitt.dbmi.hbService.models.Person;
import com.edu.pitt.dbmi.hbService.services.PersonService;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Main {
	
	// Creates the instance to the resource service
	PersonService personService = new PersonService();

	// Method to handle the Http GET request
	// This method returns the entire database of Person
	@GET
	public List<Person> getAllPersons() {
		return personService.getAllPersons();
	}
	
	// Method to handle the Http GET request
	// This method returns the person id for the given source id in the url.
	// It follows the url http://localhost:9090/hbService/main/sourceId/{sourceId}
	@GET
	@Path("sourceId/{sourceId}")
	public List<Person> getPersonBySourceId(@PathParam("sourceId") int sourceId) {
		return personService.getPersonBySourceId(sourceId);
	}
	
	// Method to handle Http GET request
	// This method returns the all the persons for the given source type
	@GET
	@Path("{sourceType}")
	public List<Person> getPersonBySourceType(@PathParam("sourceType") String sourceType) {
		return personService.getAllBySourceType(sourceType);
	}
	
	// Method to handle Http GET request
	// This method returns the person id for the given source type and source id in the url
	@GET
	@Path("{sourceType}/{sourceId}")
	public List<Person> getPersonBySource(@PathParam("sourceType") String sourceType, @PathParam("sourceId") int sourceId) {
		// Determine the source type
		return personService.getPersonBySource(sourceType, sourceId);
			
	}
	
	// Method to handle Http POST request
	// This method will find a member with the research id and assign the source id
	@PUT
	@Path("add")
	public Response addSource(@QueryParam("sourceType") String sourceType,
								  @QueryParam("sourceId") int sourceId,
								  @QueryParam("researchId") int researchId,
								  @Context UriInfo uriInfo) throws SQLException {
		personService.addSource(sourceType, sourceId, researchId);
		URI uri = uriInfo.getAbsolutePathBuilder().path(sourceType).path(String.valueOf(sourceId)).build();
		return Response.created(uri).build();
	}
}
