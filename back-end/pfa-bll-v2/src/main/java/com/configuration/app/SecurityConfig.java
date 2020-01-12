package com.configuration.app;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.configuration.jwt.JwtAuthenticationEntryPoint;
import com.configuration.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource securityDataSource;

	@Autowired
	private UserDetailsService userServiceImp;
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// use jdbc based authentification
		auth.jdbcAuthentication().dataSource(securityDataSource);

		// use the custom authetification
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationFilter();
    }
	
	 @Override
	 @Bean
	 public AuthenticationManager authenticationManagerBean() throws Exception {
		 return super.authenticationManagerBean();
	 }
	
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").hasAnyRole("ADMIN", "TOURIST")
		.antMatchers("/admins/**").hasRole("ADMIN")
		.antMatchers("/tourists/**").hasRole("TOURIST")
		.antMatchers("/user/register", "/user/confirmRegistration", "/user/showLoginPage",
				"/user/accessDenied").permitAll()
		.antMatchers("/user/deleteUser").hasRole("TOURIST")
		.and()
		.formLogin().loginPage("/user/showLoginPage")
		.loginProcessingUrl("/user/authenticateTheUser").permitAll()
		.and()
		.logout().logoutUrl("/user/performLogout").logoutSuccessUrl("/user/showLoginPage")
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/user/accessDenied");
	}*/
	
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//		.authorizeRequests()
//		.antMatchers("/").permitAll()
//		.antMatchers("/api/all/**").permitAll()
//		.antMatchers("/api/users/**").hasRole("TOURIST")
//		.and()
//		.formLogin().loginProcessingUrl("/api/all/authenticate").permitAll()
//		.and()
//		.logout().logoutUrl("/api/users/perform-logout").logoutSuccessUrl("/api/users/success").permitAll()
//		.and()
//		.exceptionHandling().accessDeniedPage("/api/users/access-denied");
//	}
	
	/*
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/api/all/**").permitAll()
		.antMatchers("/api/users/**").hasRole("TOURIST")
		.anyRequest().authenticated()
		.and()
		.logout().logoutUrl("/api/users/perform-logout").logoutSuccessUrl("/api/users/success").permitAll()
		.logoutSuccessUrl("/api/all/success").deleteCookies("JSESSIONID")
		.invalidateHttpSession(true)
		.and()
		.httpBasic();
	}
	*/
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
        .authorizeRequests()
        .antMatchers("/").permitAll()
		.antMatchers("/api/all/**").permitAll()
		.antMatchers("/api/users/**").hasRole("TOURIST")
        .anyRequest().authenticated()
        .and()
        .logout()
        .and()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.addFilterBefore((Filter)authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
	
	// build a custom authentication provider
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userServiceImp);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	// use bcrypt encryption with 11 rounds
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}
}
