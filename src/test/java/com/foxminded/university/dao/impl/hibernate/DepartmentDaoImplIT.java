package com.foxminded.university.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foxminded.university.config.TestDataConfiguration;
import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.domain.Department;
import com.foxminded.university.service.DepartmentRepository;

@ContextConfiguration(classes = { TestDataConfiguration.class })
@ExtendWith(SpringExtension.class)
public class DepartmentDaoImplIT {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private FacultyDao facultyDao;
    @Autowired
    private Flyway flyway;
    private Department testDepartment = DepartmentRepository.getTestDepartment();
    private Department otherDepartment = DepartmentRepository.getTestDepartment();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        facultyDao.add(testDepartment.getFaculty());
        departmentDao.add(testDepartment);
        otherDepartment.setTitle("Optics");
        otherDepartment.setId(2);
        departmentDao.add(otherDepartment);

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
    public void shouldDeleteDepartmentById() throws Exception {
        departmentDao.delete(1);
        List<Department> expected = new ArrayList<>();
        expected.add(otherDepartment);
        Assertions.assertEquals(expected, departmentDao.getAll());
    }

    @Test
    public void shouldSetDepartmentId() throws Exception {

        Department expected = new Department();
        expected.setTitle("test");
        departmentDao.add(expected);
        Assertions.assertEquals(4, expected.getId());
    }

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }

}
