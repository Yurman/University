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
    private FacultyRepository facultyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private Flyway flyway;
    private Department testDepartment = DepartmentInit.getTestDepartment();
    private Department otherDepartment = DepartmentInit.getTestDepartment();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        Faculty faculty = FacultyInit.getTestFaculty();
        facultyRepository.save(faculty);
        testDepartment.setFaculty(faculty);
        departmentRepository.save(testDepartment);
        otherDepartment.setTitle("Optics");
        otherDepartment.setFaculty(faculty);
        otherDepartment.setDeleted(true);
        departmentRepository.save(otherDepartment);
        otherDepartment.setId(2);
    }

    @Test
    public void shouldGetDepartmentById() throws Exception {
        Assertions.assertEquals(testDepartment, departmentRepository.findById(1));
    }

    @Test
    public void shouldGetAllDepartments() throws Exception {
        List<Department> expected = new ArrayList<>();
        expected.add(testDepartment);
        expected.add(otherDepartment);

        Assertions.assertEquals(expected, departmentRepository.findAll());
    }

    @Test
    public void shouldGetAllUndeletedDepartments() throws Exception {
        assertThat(departmentRepository.findAllUndeleted()).hasSize(1).contains(testDepartment);
    }

    @Test
    public void shouldUpdteDepartment() throws Exception {
        testDepartment.setTitle("Math");
        departmentRepository.save(testDepartment);

        Assertions.assertEquals(testDepartment, departmentRepository.findById(1));
    }

    @Test
    public void shouldDeleteDepartmentById() throws Exception {
        departmentRepository.deleteById(1);
        assertThat(departmentRepository.findById(1)).isNull();
    }

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }

}
