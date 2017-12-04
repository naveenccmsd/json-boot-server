package com.ccmsd.starters.rest.api.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ccmsd.starters.rest.api.TestApi;

@Component
@Scope("request")
public class TestImpl implements TestApi
{

	@Autowired
    private EchoMessageCreator echoer;
	
	@Override
	public Response loadTaxHistory(String transactionId)
	{
		return Response.ok(echoer.createEchoMessage(transactionId)).build();
	}

}
