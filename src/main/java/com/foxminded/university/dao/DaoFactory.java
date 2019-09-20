package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {
    public Connection getConnection() {
	try {
	    Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
	String url = "jdbc:postgresql://localhost/university";
	Properties properties = new Properties();
	properties.setProperty("user", "admin");
	properties.setProperty("password", "password");
	Connection connection = null;

	try {
	    connection = DriverManager.getConnection(url, properties);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return connection;
    }
}
