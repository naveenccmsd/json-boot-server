package com.ccmsd.starters.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ccmsd.starters.rest.dao.entity.AuthUser;

@Component
public interface AuthUserRepository extends JpaRepository<AuthUser, Long>
{
	AuthUser findByUsername( String username );
}
