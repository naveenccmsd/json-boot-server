package com.ccmsd.starters.init;

import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Component
@Provider
public class CustomContainerResponseFilter implements ContainerResponseFilter
{

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException
	{
		// Checks if request has a HTTP header named "ping".
		// If it does, adds an HTTP header named "pong" to the response.
		// The header value is irrelevant.
		if (requestContext.getHeaderString("ping") != null)
		{
			responseContext.getHeaders().add("pong", "pong");
		}
	}

}
