package com.foxminded.university.service;

import java.util.Properties;

import org.flywaydb.core.Flyway;

import com.foxminded.university.util.PropertyReader;

public class FlywayWrapper {

    public static Flyway initializeFlyway() {
        Properties prop = PropertyReader.readProperties("db.properties");
        return Flyway.configure().dataSource(prop.getProperty("flyway.url"), prop.getProperty("flyway.user"),
                prop.getProperty("flyway.password")).load();
    }
}
