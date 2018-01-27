package com.ccmsd.starters.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.ccmsd.starters.security.auth.AuthUserService;
import com.ccmsd.starters.security.auth.RestAuthenticationEntryPoint;
import com.ccmsd.starters.security.auth.TokenAuthenticationFilter;
import com.ccmsd.starters.security.auth.TokenHelper;

@Configuration("MySecurityConfig")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter
{

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private AuthUserService jwtUserDetailsService;
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Autowired
	TokenHelper tokenHelper;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		List<RequestMatcher> csrfMethods = new ArrayList<>();
		Arrays.asList("POST", "PUT", "PATCH", "DELETE")
				.forEach(method -> csrfMethods.add(new AntPathRequestMatcher("/**", method)));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.authenticationEntryPoint(restAuthenticationEntryPoint).and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css",
						"/**/*.js")
				.permitAll().antMatchers("/v1/auth/**").permitAll().anyRequest().authenticated().and()
				.addFilterBefore(new TokenAuthenticationFilter(tokenHelper, jwtUserDetailsService),
						BasicAuthenticationFilter.class);

		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception
	{
		// TokenAuthenticationFilter will ignore the below paths
		web.ignoring().antMatchers(HttpMethod.POST, "/v1/auth/login");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");

	}

	// @Override
	// protected void configure(AuthenticationManagerBuilder builder) throws Exception
	// {
	// builder.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin")
	// .password("admin").roles("ADMIN");
	// }

	// @Override
	// protected void configure(HttpSecurity http) throws Exception
	// {
	// http.authorizeRequests().antMatchers("/v1/**").authenticated();
	// http.csrf().disable();
	// http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
	// http.formLogin().successHandler(authenticationSuccessHandler);
	// http.formLogin().failureHandler(authenticationFailureHandler);
	// }
}