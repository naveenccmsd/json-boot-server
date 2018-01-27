package com.ccmsd.starters.vo;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UserIdGenerator implements IdentifierGenerator
{

	@Override
	public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException
	{
		UUID uuid = UUID.randomUUID();
		return uuid.getMostSignificantBits();
	}

}
