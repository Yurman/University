package com.foxminded.university.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;

@Configuration
@ComponentScan("com.foxminded.university")
@PropertySource("classpath:jndi.properties")
public class DataConfiguration {

    @Autowired
    private Environment environment;

    private static final Logger logger = LoggerFactory.getLogger(DataConfiguration.class);

    @Bean
    DataSource dataSource() {
        DataSource dataSource = null;
        try {
            JndiTemplate template = new JndiTemplate();
            dataSource = (DataSource) template.lookup(environment.getProperty("ds.name.context"));
        } catch (NamingException e) {
            logger.error("Problem with finding DataSource", e);
        }
        return dataSource;
    }
}
