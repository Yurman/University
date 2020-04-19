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
import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.service.FacultyRepository;

@ContextConfiguration(classes = { TestDataConfiguration.class })
@ExtendWith(SpringExtension.class)
public class FacultyDaoImplIT {

    @Autowired
    private FacultyDao facultyDao;
    @Autowired
    private Flyway flyway;
    private Faculty testFaculty = FacultyRepository.getTestFaculty();
    private Faculty otherFaculty = FacultyRepository.getTestFaculty();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        facultyDao.add(testFaculty);
        otherFaculty.setTitle("Physics");
        otherFaculty.setId(2);
        facultyDao.add(otherFaculty);
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
        List<Faculty> expected = new ArrayList<>();
        expected.add(otherFaculty);
        Assertions.assertEquals(expected, facultyDao.getAll());
    }

    @AfterEach
    public void tearDown() throws Exception {

    }
}
