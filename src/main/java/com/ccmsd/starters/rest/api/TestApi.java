package com.ccmsd.starters.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/test")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public interface TestApi
{

	@GET
	@Path("/{msg}")
	Response loadTaxHistory(@PathParam("msg") String transactionId);
}
