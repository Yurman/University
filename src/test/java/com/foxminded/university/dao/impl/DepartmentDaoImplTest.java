package com.foxminded.university.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.foxminded.university.dao.exception.DaoException;
import com.foxminded.university.domain.Department;
import com.foxminded.university.service.DepartmentRepository;

public class DepartmentDaoImplTest {
    private DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
    private Flyway flyway = FlywayWrapper.initializeFlyway();
    private Department testDepartment = DepartmentRepository.getTestDepartment();
    private Department otherDepartment = DepartmentRepository.getTestDepartment();

    @Before
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
        assertEquals(testDepartment, departmentDao.getById(1));
    }

    @Test
    public void shouldGetAllDepartments() throws Exception {
        List<Department> expected = new ArrayList<>();
        expected.add(testDepartment);
        expected.add(otherDepartment);

        assertEquals(expected, departmentDao.getAll());
    }

    @Test
    public void shouldUpdteDepartment() throws Exception {
        testDepartment.setTitle("Math");
        departmentDao.update(testDepartment);

        assertEquals(testDepartment, departmentDao.getById(1));
    }

    @Test(expected = DaoException.class)
    public void shouldDeleteFacultyById() throws Exception {
        departmentDao.delete(1);
        departmentDao.getById(1);
    }

    @After
    public void tearDown() throws Exception {
        flyway.clean();
    }

}
