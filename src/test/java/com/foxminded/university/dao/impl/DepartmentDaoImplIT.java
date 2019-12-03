package com.foxminded.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.foxminded.university.dao.exception.DaoException;
import com.foxminded.university.domain.Department;
import com.foxminded.university.service.DepartmentRepository;

public class DepartmentDaoImplIT {
    private DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
    private Flyway flyway = FlywayWrapper.initializeFlyway();
    private Department testDepartment = DepartmentRepository.getTestDepartment();
    private Department otherDepartment = DepartmentRepository.getTestDepartment();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        FacultyDaoImpl facultyDao = new FacultyDaoImpl();
        facultyDao.add(testDepartment.getFaculty());

        departmentDao.add(testDepartment);
        otherDepartment.setTitle("Optics");
        departmentDao.add(otherDepartment);
        otherDepartment.setId(2);
    }

    @Test
    public void shouldGetDepartmentById() throws Exception {
        Assertions.assertEquals(testDepartment, departmentDao.getById(1));
    }

    @Test
    public void shouldGetAllDepartments() throws Exception {
        List<Department> expected = new ArrayList<>();
        expected.add(testDepartment);
        expected.add(otherDepartment);

        Assertions.assertEquals(expected, departmentDao.getAll());
    }

    @Test
    public void shouldUpdteDepartment() throws Exception {
        testDepartment.setTitle("Math");
        departmentDao.update(testDepartment);

        Assertions.assertEquals(testDepartment, departmentDao.getById(1));
    }

    @Test
    public void shouldDeleteFacultyById() throws Exception {
        departmentDao.delete(1);
        Assertions.assertThrows(DaoException.class, () -> {
            departmentDao.getById(1);
        });
    }

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }

}
