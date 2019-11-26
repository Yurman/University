package com.foxminded.university.dao.impl;

import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import com.foxminded.university.dao.DaoFactory;

public class FlywayWrapper {

    public static Flyway initializeFlyway() {

        Flyway flyway = new Flyway();
        BasicDataSource dataSource = new BasicDataSource();
        DaoFactory factory = new DaoFactory();
        Properties testProperties = factory.readProperties("db.properties");
        dataSource.setDriverClassName(testProperties.getProperty("db.driver"));
        dataSource.setUrl(testProperties.getProperty("db.url"));
        dataSource.setUsername(testProperties.getProperty("db.user"));
        dataSource.setPassword(testProperties.getProperty("db.password"));
        flyway.setDataSource(dataSource);
        return flyway;
    }
}
