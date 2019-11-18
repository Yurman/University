package com.foxminded.university.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentRepository;

public class StudentDaoImplTest {

    private StudentDaoImpl studentDao = new StudentDaoImpl();
    private Flyway flyway = FlywayWrapper.initializeFlyway();
    private Student testStudent = StudentRepository.getDaoTestStudent();
    private Student otherStudent = StudentRepository.getDaoTestStudent();

    @Before
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        FacultyDaoImpl facultyDao = new FacultyDaoImpl();
        facultyDao.add(testStudent.getGroup().getDepartment().getFaculty());

        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
        departmentDao.add(testStudent.getGroup().getDepartment());

        GroupDaoImpl groupDao = new GroupDaoImpl();
        groupDao.add(testStudent.getGroup());

        studentDao.add(testStudent);
        otherStudent.setFirstName("Nick");
        otherStudent.setLastName("Tester");
        studentDao.add(otherStudent);
    }

    @Test
    public void shouldGetStudentById() throws Exception {
        assertEquals(testStudent, studentDao.getById(1));
    }

    @Test
    public void shouldUpdateStudent() throws Exception {
        testStudent.setFirstName("Nicolas");
        studentDao.update(testStudent);
        assertEquals(testStudent, studentDao.getById(1));
    }

    @Test
    public void shouldGetAllStudents() throws Exception {
        List<Student> expected = new ArrayList<>();
        expected.add(testStudent);
        expected.add(otherStudent);
        assertEquals(expected, studentDao.getAll());
    }

    @Test
    public void shouldDeleteStudent() throws Exception {
        List<Student> expected = new ArrayList<>();
        expected.add(testStudent);
        studentDao.delete(2);
        assertEquals(expected, studentDao.getAll());
    }

    @After
    public void tearDown() throws Exception {
        flyway.clean();
    }
}
