//package com.reca.todo.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.filter.CorsFilter;
//
//import com.reca.todo.security.JwtAuthenticationFilter;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Configuration 
//@EnableWebSecurity
//@Slf4j
//public class WebSecurityConfig {
//	
//	@Autowired
//	private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//	  @Bean
//	   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
//	        http.cors().and().csrf().disable().httpBasic().disable()	        
//	        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//	            .authorizeRequests() 
//	            .antMatchers("/", "/auth/**","/js/**","/css/**").permitAll()
//	            .antMatchers("/").authenticated()
//	            .anyRequest().authenticated();
//	      http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
//	      return http.build();
//	   }
//}
