//package com.pdh.security;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableAutoConfiguration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	DataSource dataSource;
//	
//	@Autowired
//	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource)
//				.usersByUsernameQuery("select user_name, senha from funcionario where user)name=?");
////				.authoritiesByUsernameQuery("select user_name, senha from funcionario where user_name=?");
//	}
// 
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/", "/home").permitAll().antMatchers("/funcionario").hasRole("")
//				.anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/funcionarios").failureUrl("/login?error").permitAll().and().logout()
//				.permitAll();
//		http.exceptionHandling().accessDeniedPage("/403");
//	}
//
//}