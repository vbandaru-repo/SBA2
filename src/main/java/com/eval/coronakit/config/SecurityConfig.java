package com.eval.coronakit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

	@SuppressWarnings("deprecation")
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	
	 	UserBuilder builder =  User.withDefaultPasswordEncoder();
	 	auth.inMemoryAuthentication()
	 		.withUser(builder.username("admin").password("admin").roles("ADMIN"))
	 		.withUser(builder.username("venkata").password("abcd").roles("USER"))
	 		.withUser(builder.username("ramana").password("abc").roles("USER"));
	} 
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().and()
        .authorizeRequests().antMatchers("/console/**").permitAll();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		// Add Your Application Based Security config here
		http.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/user/**").hasRole("USER")
			.and()
			.formLogin()
			.loginPage("/custom-login")
			.loginProcessingUrl("/validate")
			.defaultSuccessUrl("/home")
			.permitAll()
			.and()
			.logout()
			.permitAll()
			.and()
			.exceptionHandling()
			.accessDeniedPage("/custom-error");
	}
}

