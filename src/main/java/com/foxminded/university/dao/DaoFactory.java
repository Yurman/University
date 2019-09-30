package com.foxminded.university.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {
    public Connection getConnection() {
	FileInputStream fileInput = null;
	Properties dbProperty = new Properties();

	try {
	    fileInput = new FileInputStream("src/main/resources/db.properties");
	    dbProperty.load(fileInput);

	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		fileInput.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	try {
	    Class.forName(dbProperty.getProperty("db.driver"));
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}

	Properties connectionProperty = new Properties();
	connectionProperty.setProperty("user", dbProperty.getProperty("db.user"));
	connectionProperty.setProperty("password", dbProperty.getProperty("db.password"));
	Connection connection = null;

	try {
	    connection = DriverManager.getConnection(dbProperty.getProperty("db.url"), connectionProperty);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return connection;
    }
}
