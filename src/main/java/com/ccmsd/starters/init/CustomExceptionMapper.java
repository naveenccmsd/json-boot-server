package com.ccmsd.starters.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccmsd.starters.vo.ErrorMessage;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Custom exception mapper for 404 cases.
 *
 * @author Fabio Carvalho (facarvalho@paypal.com or fabiocarvalho777@gmail.com)
 */
@Component
@Provider
public class CustomExceptionMapper implements ExceptionMapper<Exception>
{

	@Autowired
	private CustomSingletonBean customSingletonBean;

	@Override
	public Response toResponse(Exception ex)
	{
		customSingletonBean.amIAlive();
		ex.printStackTrace();
		if (ex instanceof NotFoundException)
		{
			return buildException(Response.Status.NOT_FOUND, "The resource you've requested, has not been found!");
		}
		if (ex instanceof NotAllowedException)
		{
			return buildException(Response.Status.METHOD_NOT_ALLOWED, ex.getMessage());
		} else
		{
			return buildException(Response.Status.INTERNAL_SERVER_ERROR, ex.getMessage());
		}

	}

	private Response buildException(Status status, Object entity)
	{
		ErrorMessage error = new ErrorMessage();
		error.setStatus(status.getStatusCode());
		error.setError(status.getReasonPhrase());
		if (entity instanceof String)
			error.setMessage((String) entity);
		else
			error.setAdditionalProperty("details", entity);
		Response.ResponseBuilder responseBuilder = Response.status(status).entity(error);
		responseBuilder.type(MediaType.APPLICATION_JSON);
		return responseBuilder.build();
	}

}
