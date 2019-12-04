package com.foxminded.university.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.foxminded.university")
@PropertySource("classpath:db.properties")
public class SpringConfigurator {

    @Autowired
    Environment environment;

    private final String URL = "db.url";
    private final String USER = "db.user";
    private final String DRIVER = "db.driver";
    private final String PASSWORD = "db.password";

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty(URL));
        dataSource.setUsername(environment.getProperty(USER));
        dataSource.setPassword(environment.getProperty(PASSWORD));
        dataSource.setDriverClassName(environment.getProperty(DRIVER));
        return dataSource;
    }
}
