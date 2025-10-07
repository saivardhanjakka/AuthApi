package com.sv.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sv.auth.service.UserService;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserService userService;
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(encoder());
		authProvider.setUserDetailsService(userService);
		return authProvider;
			
			
		}
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
		
	}
	@Bean
	public SecurityFilterChain security(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((req)->{
			req.requestMatchers("/register","/login")
			.permitAll()
			.anyRequest()
			.authenticated();
		});
		return http.csrf().disable().build();
		
	}

	

}
