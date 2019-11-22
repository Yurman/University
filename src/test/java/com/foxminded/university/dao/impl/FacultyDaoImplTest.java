package com.foxminded.university.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.foxminded.university.dao.exception.DaoException;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.service.FacultyRepository;

public class FacultyDaoImplTest {
    private FacultyDaoImpl facultyDao = new FacultyDaoImpl();
    private Flyway flyway = FlywayWrapper.initializeFlyway();
    private Faculty testFaculty = FacultyRepository.getTestFaculty();
    private Faculty otherFaculty = FacultyRepository.getTestFaculty();

    @Before
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
        assertEquals(testFaculty, facultyDao.getById(1));
    }

    @Test
    public void shouldGetAllFaculties() throws Exception {
        List<Faculty> expected = new ArrayList<>();
        expected.add(testFaculty);
        expected.add(otherFaculty);

        assertEquals(expected, facultyDao.getAll());
    }

    @Test
    public void shouldUpdteFaculty() throws Exception {
        testFaculty.setTitle("Math");
        facultyDao.update(testFaculty);

        assertEquals(testFaculty, facultyDao.getById(1));
    }

    @Test(expected = DaoException.class)
    public void shouldDeleteFacultyById() throws DaoException {
        facultyDao.delete(1);
        facultyDao.getById(1);
    }

    @After
    public void tearDown() throws Exception {
        flyway.clean();
    }
}
