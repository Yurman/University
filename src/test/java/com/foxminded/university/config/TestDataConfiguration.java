package com.foxminded.university.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.foxminded.university.util.PropertyReader;

@Configuration
@ComponentScan("com.foxminded.university")
@PropertySource("classpath:application.properties")
public class TestDataConfiguration {

    @Autowired
    private Environment environment;

    private final String URL = "db.url";
    private final String USER = "db.user";
    private final String DRIVER = "db.driver";
    private final String PASSWORD = "db.password";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty(URL));
        dataSource.setUsername(environment.getProperty(USER));
        dataSource.setPassword(environment.getProperty(PASSWORD));
        dataSource.setDriverClassName(environment.getProperty(DRIVER));
        return dataSource;
    }

    @Bean
    public Flyway initializeFlyway() {
        Properties prop = PropertyReader.readProperties("application.properties");
        return Flyway.configure().dataSource(prop.getProperty("flyway.url"), prop.getProperty("flyway.user"),
                prop.getProperty("flyway.password")).load();
    }

}
