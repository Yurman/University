package com.foxminded.university.dao.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.foxminded.university.domain.Student;

public class StudentDaoImplTest {

    private StudentDaoImpl studentDao = new StudentDaoImpl();
    private TestDataInitializer testData = new TestDataInitializer();
    private Flyway flyway = TestDataInitializer.initializeFlyway();

    @Before
    public void setUp() throws Exception {
	flyway.clean();
	flyway.migrate();
	testData.initializeTestData();
    }

    @Test
    public void shouldGetStudentById() throws Exception {
	Student student = studentDao.getById(1);
	assertTrue(student.getFirstName().equals("Anatoliy"));
	assertTrue(student.getLastName().equals("Shyrokov"));
    }

    @Test
    public void shouldUpdateStudent() throws Exception {
	Student student = testData.makeTestStudent();
	student.setId(1);
	student.setFirstName("Tolya");
	Student updatedStudent = studentDao.update(student);
	assertTrue(updatedStudent.getFirstName().equals("Tolya"));
	assertTrue(updatedStudent.getLastName().equals("Shyrokov"));
    }

    @Test
    public void shouldGetAllStudents() throws Exception {
	List<Student> students = studentDao.getAll();
	assertTrue(students.size() == 23);
    }

    @Test
    public void shouldDeleteStudent() throws Exception {
	studentDao.delete(22);
	List<Student> students = studentDao.getAll();
	assertTrue(students.size() == 22);
    }

    @Test
    public void shouldAddStudent() throws Exception {
	Student student = testData.makeTestStudent();
	student.setFirstName("test");
	Student addedStudent = studentDao.add(student);
	assertTrue(addedStudent.getId() == 24);	
	assertTrue(studentDao.getById(24).getFirstName().equals("test"));
    }

    @After
    public void tearDown() throws Exception {
	flyway.clean();
    }
}
