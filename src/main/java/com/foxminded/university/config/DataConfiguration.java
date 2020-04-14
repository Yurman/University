package com.foxminded.university.config;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;

@Configuration
@ComponentScan("com.foxminded.university")
@PropertySource("classpath:application.properties")
public class DataConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() throws NamingException {

        return (DataSource) new JndiTemplate().lookup(environment.getProperty("ds.name.context"));
    }

    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("university");
    }

}
