package com.ccmsd.starters.rest.api.impl;

import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ccmsd.starters.rest.api.TestApi;
import com.ccmsd.starters.vo.EchoMessage;

@Component
@Scope("request")
public class TestImpl implements TestApi
{

	@Override
	public Response loadTaxHistory(String echoText)
	{
		return Response.ok(new EchoMessage(echoText)).build();
	}

}
