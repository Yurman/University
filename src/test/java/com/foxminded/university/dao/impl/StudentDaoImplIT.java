package com.foxminded.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import com.foxminded.university.dao.SpringConfigurator;
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentRepository;

public class StudentDaoImplIT {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurator.class);
    private StudentDaoImpl studentDao = context.getBean(StudentDaoImpl.class);
    private Flyway flyway = FlywayWrapper.initializeFlyway();
    private Student testStudent = StudentRepository.getDaoTestStudent();
    private Student otherStudent = StudentRepository.getDaoTestStudent();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        FacultyDaoImpl facultyDao = new FacultyDaoImpl();
        facultyDao.add(testStudent.getGroup().getDepartment().getFaculty());

        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
        departmentDao.add(testStudent.getGroup().getDepartment());

        GroupDaoImpl groupDao = context.getBean(GroupDaoImpl.class);
        groupDao.add(testStudent.getGroup());
        studentDao.add(testStudent);
        otherStudent.setFirstName("Nick");
        otherStudent.setLastName("Tester");
        studentDao.add(otherStudent);
    }

    @Test
    public void shouldGetStudentById() throws Exception {
        Assertions.assertEquals(testStudent, studentDao.getById(1));
    }

    @Test
    public void shouldUpdateStudent() throws Exception {
        testStudent.setFirstName("Nicolas");
        studentDao.update(testStudent);
        Assertions.assertEquals(testStudent, studentDao.getById(1));
    }

    @Test
    public void shouldGetAllStudents() throws Exception {
        List<Student> expected = new ArrayList<>();
        expected.add(testStudent);
        expected.add(otherStudent);
        Assertions.assertEquals(expected, studentDao.getAll());
    }

    @Test
    public void shouldDeleteStudent() throws Exception {
        studentDao.delete(2);
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            studentDao.getById(2);
        });
    }

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }
}
