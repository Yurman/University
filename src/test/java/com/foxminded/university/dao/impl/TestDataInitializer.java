package com.foxminded.university.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;

public class TestDataInitializer {

    public static Flyway initializeFlyway() {
	Flyway flyway = new Flyway();
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName("org.postgresql.Driver");
	dataSource.setUrl("jdbc:postgresql://localhost:5433/test_university");
	dataSource.setUsername("admin");
	dataSource.setPassword("password");
	flyway.setDataSource(dataSource);
	flyway.setSchemas("test_university");
	return flyway;
    }

    public void initializeTestData() {

	try {
	    Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {

	    e.printStackTrace();
	}
	Properties connectionProperty = new Properties();
	connectionProperty.setProperty("user", "admin");
	connectionProperty.setProperty("password", "password");
	Connection connection = null;
	String sql = sqlStringReader("insert_test_data.sql");

	try {
	    connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/test_university",
		    connectionProperty);
	    PreparedStatement statement = connection.prepareStatement(sql);
	    statement.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    private String sqlStringReader(String fileName) {
	StringBuilder result = new StringBuilder();
	ClassLoader classLoader = this.getClass().getClassLoader();
	File textFile = new File(classLoader.getResource(fileName).getFile());

	try (FileInputStream inputStream = new FileInputStream(textFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
	    String line;
	    while ((line = reader.readLine()) != null) {
		result.append(line);
	    }	   
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return result.toString();
    }
}
