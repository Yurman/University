package com.foxminded.university.dao.impl;

import org.flywaydb.core.Flyway;
import com.foxminded.university.dao.DaoFactory;

public class FlywayWrapper {

    public static Flyway initializeFlyway() {
        DaoFactory factory = new DaoFactory();

        Flyway flyway = new Flyway();
        flyway.configure(factory.readProperties("flyway.properties"));
        return flyway;
    }
}
