package com.foxminded.university.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;

import com.foxminded.university.dao.DaoFactory;
import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

public class TestDataInitializer {

    public static Flyway initializeFlyway() {
	Flyway flyway = new Flyway();
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName("org.postgresql.Driver");
	dataSource.setUrl("jdbc:postgresql://localhost:5432/test_university");
	dataSource.setUsername("admin");
	dataSource.setPassword("password");
	flyway.setDataSource(dataSource);
	return flyway;
    }

    public void initializeTestData() {
	DaoFactory factory = new DaoFactory();
	String sql = sqlStringReader("insert_test_data.sql");

	try (Connection connection = factory.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);) {
	    statement.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    public Faculty makeTestFaculty() {
	Faculty faculty = new Faculty();
	faculty.setTitle("Pysics");
	faculty.setId(1);

	return faculty;
    }

    public Department makeTestDepartment() {
	Department department = new Department();
	department.setId(1);
	department.setTitle("Math and Physics");
	department.setFaculty(makeTestFaculty());

	return department;
    }

    public Group makeTestGroup() {
	Group group = new Group();
	group.setId(1);
	group.setTitle("MT-11");
	group.setYear(1);
	group.setDepartment(makeTestDepartment());

	return group;
    }

    public Student makeTestStudent() {
	Student student = new Student();
	student.setFirstName("Tolyan");
	student.setLastName("Shyrokov");
	student.setGroup(makeTestGroup());

	return student;
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
