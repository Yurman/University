package com.foxminded.university.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@EnableAutoConfiguration
@ComponentScan("com.foxminded.university")
@PropertySource("classpath:application.properties")
public class TestDataConfiguration {

    @Autowired
    private Environment environment;

    private final String URL = "spring.datasource.url";
    private final String USER = "spring.datasource.user";
    private final String DRIVER = "spring.datasource.driver";
    private final String PASSWORD = "spring.datasource.password";

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
    @ConditionalOnMissingBean(value = org.flywaydb.core.Flyway.class)
    public Flyway initializeFlyway() {
        return Flyway.configure()
                .dataSource(environment.getProperty("spring.flyway.url"), environment.getProperty("spring.flyway.user"),
                        environment.getProperty("spring.flyway.password"))
                .load();
    }

}
