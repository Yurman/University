package com.foxminded.university.dao.impl;

import static org.junit.Assert.assertThat;
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
    public void testAdd() throws Exception {
	
    }
    
    @Test
    public void testGetAll() throws Exception {
	List<Student> students = studentDao.getAll();
	assertTrue(students.size() > 0);
	assertTrue(students.size() == 22);
    }

    @Test
    public void testDelete() throws Exception {
	studentDao.delete(289);
	List<Student> students = studentDao.getAll();	
	assertTrue(students.size() == 22);
    }

    @After
    public void tearDown() throws Exception {
	flyway.clean();
    }
}
