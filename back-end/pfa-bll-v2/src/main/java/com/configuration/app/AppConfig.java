package com.configuration.app;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.entities.Authority;
import com.entities.Circuit;
import com.entities.Comment;
import com.entities.Monument;
import com.entities.Region;
import com.entities.Tourist;
import com.entities.User;
import com.entities.VerificationToken;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * This is the equivalent of the xml spring-mvc configuration file in the Java
 * source code cofiguration
 *
 */

@EnableWebMvc
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = { "com.controllers", "com.services", "com.dao", "com.events", "com.rest",
		"com.configuration.jwt" })
public class AppConfig {

	// set up a variable to hold the properties
	@Autowired
	private Environment env;

	// set up a logger
	private Logger logger = Logger.getLogger(getClass().getName());

	// Define a bean for the view resolver
	// Just for testing
	/*
	 * @Bean public ViewResolver viewResolver() {
	 * 
	 * InternalResourceViewResolver viewResolver = new
	 * InternalResourceViewResolver();
	 * 
	 * viewResolver.setPrefix("/WEB-INF/view/"); viewResolver.setSuffix(".jsp");
	 * 
	 * return viewResolver; }
	 */

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedOrigins("http://localhost:4200");
			}
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	private Properties getHibernateProperties() {

		Properties props = new Properties();

		// log hibernate properties
		logger.info("hibernate.hbm2ddl.auto >> " + env.getProperty("hibernate.hbm2ddl.auto"));
		logger.info("hibernate.dialect >> " + env.getProperty("hibernate.dialect"));
		logger.info("hibernate.show_sql >> " + env.getProperty("hibernate.show_sql"));

		// set hibernate properties
		props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

		return props;
	}

	// define a bean for our data source
	@Bean
	public DataSource getDataSource() {

		// create connection pool
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		// set the jdbc driver class
		try {
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

		// log the jdbc connection properties
		logger.info("jdbc.url >> " + env.getProperty("jdbc.url"));
		logger.info("jdbc.user >> " + env.getProperty("jdbc.user"));

		// set DB connection properties
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUser(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));

		// set connection pool properties
		dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
		dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
		dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));

		return dataSource;
	}

	// define a bean to create the session factory
	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(getHibernateProperties());
		sessionFactory.setAnnotatedClasses(Circuit.class, Comment.class, Monument.class, Region.class, Tourist.class,
				User.class, Authority.class, VerificationToken.class);

		return sessionFactory;

	}

	// define a bean to bind a hibernate session from the thread
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	@Bean(name = "mailSender")
	public MailSender javaMailService() {

		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(587);
		javaMailSender.setProtocol("smtp");
		javaMailSender.setUsername("tourism.app.ensah@gmail.com");
		javaMailSender.setPassword("Ensah_2019$");

		Properties mailProperties = new Properties();

		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.smtp.starttls.enable", "true");
		mailProperties.put("mail.smtp.debug", "true");
		javaMailSender.setJavaMailProperties(mailProperties);

		return javaMailSender;
	}

	@Bean
	public MessageSource messageSource() {

		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("classpath:messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);

		return messageSource;
	}

}
