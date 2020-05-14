package com.foxminded.university.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.service.DepartmentInit;
import com.foxminded.university.service.FacultyInit;

@SpringBootTest
public class DepartmentRepositoryIT {

    @Autowired
    private FacultyRepository facultyDao;

    @Autowired
    private DepartmentRepository departmentDao;

    @Autowired
    private Flyway flyway;
    private Department testDepartment = DepartmentInit.getTestDepartment();
    private Department otherDepartment = DepartmentInit.getTestDepartment();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        Faculty faculty = FacultyInit.getTestFaculty();
        facultyDao.save(faculty);
        testDepartment.setFaculty(faculty);
        departmentDao.save(testDepartment);
        otherDepartment.setTitle("Optics");
        otherDepartment.setFaculty(faculty);
        departmentDao.save(otherDepartment);
        otherDepartment.setId(2);
    }

    @Test
    public void shouldGetDepartmentById() throws Exception {
        Assertions.assertEquals(testDepartment, departmentDao.findById(1));
    }

    @Test
    public void shouldGetAllDepartments() throws Exception {
        List<Department> expected = new ArrayList<>();
        expected.add(testDepartment);
        expected.add(otherDepartment);

        Assertions.assertEquals(expected, departmentDao.findAll());
    }

    @Test
    public void shouldUpdteDepartment() throws Exception {
        testDepartment.setTitle("Math");
        departmentDao.save(testDepartment);

        Assertions.assertEquals(testDepartment, departmentDao.findById(1));
    }

    @Test
    public void shouldDeleteDepartmentById() throws Exception {
        departmentDao.deleteById(1);
        assertThat(departmentDao.findById(1)).isNull();
    }

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }

}
