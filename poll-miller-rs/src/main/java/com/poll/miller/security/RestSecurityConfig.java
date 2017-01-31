package com.poll.miller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/*@Configuration
@EnableWebSecurity*/
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Autowired
	private MySavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("anunay").password("anunay").roles("ADMIN")
			.and()
			.withUser("user").password("user").roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.exceptionHandling()
			.authenticationEntryPoint(restAuthenticationEntryPoint)
			.and()
			.authorizeRequests()
			.antMatchers("/poll-miller-rs").authenticated()
			.and()
			.formLogin()
			.successHandler(authenticationSuccessHandler)
			.failureHandler(new SimpleUrlAuthenticationFailureHandler())
			.and()
			.logout();
	}
	
	@Bean
	public MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler() {
		return new MySavedRequestAwareAuthenticationSuccessHandler();
	}
	
	@Bean
	public SimpleUrlAuthenticationFailureHandler myFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler();
	}
}
