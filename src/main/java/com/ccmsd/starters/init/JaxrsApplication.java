package com.ccmsd.starters.init;

import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@Component
@ApplicationPath("/api/")
public class JaxrsApplication extends Application {
}
