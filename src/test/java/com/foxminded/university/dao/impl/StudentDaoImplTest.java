package com.foxminded.university.dao.impl;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

public class StudentDaoImplTest {

    private StudentDaoImpl studentDao = new StudentDaoImpl();
    private IDatabaseTester databaseTester;

    @Before
    public void setUp() throws Exception {
	databaseTester = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost:5433/university",
		"admin", "password");
    }

    @Test
    public void testAdd() throws Exception {
	Student student = new Student();
	student.setFirstName("Vasyl");
	student.setLastName("Koval");

	Faculty faculty = new Faculty();
	faculty.setId(1);
	faculty.setTitle("mf-11");

	Department department = new Department();
	department.setId(1);
	department.setTitle("math");
	department.setFaculty(faculty);

	Group group = new Group();
	group.setId(2);
	group.setTitle("math-and-physics");
	group.setDepartment(department);

	student.setGroup(group);
	studentDao.add(student);

	IDataSet expectedData = new FlatXmlDataSetBuilder()
		.build(ClassLoader.getSystemClassLoader().getResourceAsStream("StudentAddExpected.xml"));
	ITable expectedTable = expectedData.getTable("STUDENTS");

	ITable actualData = databaseTester.getConnection().createQueryTable("STUDENTS", "SELECT * FROM STUDENTS;");
	Assertion.assertEquals(expectedTable, actualData);
    }

    @Test
    public void testDelete() throws Exception {
	studentDao.delete(19);

	IDataSet expectedData = new FlatXmlDataSetBuilder()
		.build(ClassLoader.getSystemClassLoader().getResourceAsStream("StudentDataSet.xml"));
	ITable expectedTable = expectedData.getTable("STUDENTS");

	ITable actualData = databaseTester.getConnection().createQueryTable("STUDENTS", "SELECT * FROM STUDENTS;");
	Assertion.assertEquals(expectedTable, actualData);
    }

}
