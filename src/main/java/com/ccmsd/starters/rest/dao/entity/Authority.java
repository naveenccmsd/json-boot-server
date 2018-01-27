package com.ccmsd.starters.rest.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by fan.jin on 2016-11-03.
 */

@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority
{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "authority_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long authorityId;

	@Column(name = "authority_name")
	String authorityName;

	@Override
	public String getAuthority()
	{
		return authorityName;
	}

	public void setName(String name)
	{
		this.authorityName = name;
	}

	@JsonIgnore
	public String getName()
	{
		return authorityName;
	}

	@JsonIgnore
	public Long getId()
	{
		return authorityId;
	}

	public void setId(Long id)
	{
		this.authorityId = id;
	}

}
