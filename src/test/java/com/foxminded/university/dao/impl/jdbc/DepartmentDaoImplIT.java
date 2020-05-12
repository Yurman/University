package com.foxminded.university.dao.impl.jdbc;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.service.DepartmentRepository;
import com.foxminded.university.service.FacultyRepository;

@SpringBootTest
public class DepartmentDaoImplIT {

    @Autowired
    @Qualifier("facultyDaoJdbc")
    private FacultyDao facultyDao;

    @Autowired
    @Qualifier("departmentDaoJdbc")
    private DepartmentDao departmentDao;

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
    public void shouldDeleteDepartmentById() throws Exception {
        departmentDao.delete(1);
        assertThrows(EmptyResultDataAccessException.class, () -> {
            departmentDao.getById(1);
        });
    }

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }

}
