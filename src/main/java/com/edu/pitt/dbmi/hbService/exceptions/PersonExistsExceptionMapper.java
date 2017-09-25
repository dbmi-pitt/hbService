package com.edu.pitt.dbmi.hbService.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.edu.pitt.dbmi.hbService.models.ErrorMessage;

@Provider
public class PersonExistsExceptionMapper implements ExceptionMapper<PersonExistsException> {

	@Override
	public Response toResponse(PersonExistsException arg0) {
		ErrorMessage errorMessage = new ErrorMessage(arg0.getMessage(), 404, "no link available");
		return Response.status(Status.CONFLICT)
				.entity(errorMessage)
				.build();
	}
}
