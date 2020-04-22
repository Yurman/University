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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foxminded.university.config.TestDataConfiguration;
import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.service.DepartmentRepository;
import com.foxminded.university.service.FacultyRepository;

@ContextConfiguration(classes = { TestDataConfiguration.class })
@ExtendWith(SpringExtension.class)
public class DepartmentDaoImplIT {

    @Autowired
    @Qualifier("departmentDaoHibernate")
    private DepartmentDao departmentDao;

    @Autowired
    @Qualifier("facultyDaoHibernate")
    private FacultyDao facultyDao;

    @Autowired
    private Flyway flyway;
    private Department testDepartment = DepartmentRepository.getTestDepartment();
    private Department otherDepartment = DepartmentRepository.getTestDepartment();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        Faculty faculty = FacultyRepository.getTestFaculty();
        facultyDao.add(faculty);
        testDepartment.setFaculty(faculty);
        departmentDao.add(testDepartment);
        otherDepartment.setTitle("Optics");
        otherDepartment.setFaculty(faculty);
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

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }

}
