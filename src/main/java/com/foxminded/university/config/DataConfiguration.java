package com.foxminded.university.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;

@Configuration
@ComponentScan("com.foxminded.university")
public class DataConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DataConfiguration.class);

    @Bean
    DataSource dataSource() {
        DataSource dataSource = null;
        JndiTemplate jndi = new JndiTemplate();
        try {
            dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/university");
        } catch (NamingException e) {
            logger.error("Problem with finding DataSource", e);
        }
        return dataSource;
    }
}
