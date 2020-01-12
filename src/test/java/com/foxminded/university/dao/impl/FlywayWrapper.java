package com.foxminded.university.dao.impl;

import org.flywaydb.core.Flyway;

import com.foxminded.university.config.PropertyReader;

public class FlywayWrapper {

    public static Flyway initializeFlyway() {
        Flyway flyway = new Flyway();
        flyway.configure(PropertyReader.readProperties("db.properties"));
        return flyway;
    }
}
