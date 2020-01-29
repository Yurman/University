package com.foxminded.university.util;

import java.util.Properties;

import org.flywaydb.core.Flyway;

public class FlywayWrapper {

    public static Flyway initializeFlyway() {
        Properties prop = PropertyReader.readProperties("db.properties");
        return Flyway.configure().dataSource(prop.getProperty("flyway.url"), prop.getProperty("flyway.user"),
                prop.getProperty("flyway.password")).load();
    }
}
