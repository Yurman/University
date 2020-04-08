package com.foxminded.university.config;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.foxminded.university")
@PropertySource("classpath:jndi.properties")
public class DataConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DataConfiguration.class);

    @Bean
    DataSource dataSource() {
        DataSource dataSource = null;
        try {
            InitialContext context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/university");
        } catch (NamingException e) {
            logger.error("Problem with finding DataSource", e);
        }
        return dataSource;
    }
}
