package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {

    public Connection getConnection() {
        Properties dbProperties = PropertyReader.readProperties("db.properties");
        try {
            Class.forName(dbProperties.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Properties connectionProperty = new Properties();
        connectionProperty.setProperty("user", dbProperties.getProperty("db.user"));
        connectionProperty.setProperty("password", dbProperties.getProperty("db.password"));
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbProperties.getProperty("db.url"), connectionProperty);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
