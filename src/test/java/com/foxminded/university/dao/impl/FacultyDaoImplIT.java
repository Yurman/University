package com.foxminded.university.dao.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import com.foxminded.university.config.DataConfiguration;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.service.FacultyRepository;

public class FacultyDaoImplIT {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataConfiguration.class);
    private FacultyDaoImpl facultyDao = context.getBean(FacultyDaoImpl.class);
    private Flyway flyway = FlywayWrapper.initializeFlyway();
    private Faculty testFaculty = FacultyRepository.getTestFaculty();
    private Faculty otherFaculty = FacultyRepository.getTestFaculty();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        facultyDao.add(testFaculty);
        otherFaculty.setTitle("Physics");
        facultyDao.add(otherFaculty);
        otherFaculty.setId(2);
    }

    @Test
    public void shouldGetFacultyById() throws Exception {
        Assertions.assertEquals(testFaculty, facultyDao.getById(1));
    }

    @Test
    public void shouldGetAllFaculties() throws Exception {
        List<Faculty> expected = new ArrayList<>();
        expected.add(testFaculty);
        expected.add(otherFaculty);

        Assertions.assertEquals(expected, facultyDao.getAll());
    }

    @Test
    public void shouldUpdteFaculty() throws Exception {
        testFaculty.setTitle("Math");
        facultyDao.update(testFaculty);

        Assertions.assertEquals(testFaculty, facultyDao.getById(1));
    }

    @Test
    public void shouldDeleteFacultyById() throws Exception {
        facultyDao.delete(1);
        assertThrows(EmptyResultDataAccessException.class, () -> {
            facultyDao.getById(1);
        });
    }

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }
}
