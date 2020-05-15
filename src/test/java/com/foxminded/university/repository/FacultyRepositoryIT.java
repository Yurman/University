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

import com.foxminded.university.domain.Faculty;
import com.foxminded.university.service.FacultyInit;

@SpringBootTest
public class FacultyRepositoryIT {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private Flyway flyway;
    private Faculty testFaculty = FacultyInit.getTestFaculty();
    private Faculty otherFaculty = FacultyInit.getTestFaculty();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        facultyRepository.save(testFaculty);
        otherFaculty.setTitle("Physics");
        facultyRepository.save(otherFaculty);
        otherFaculty.setId(2);
    }

    @Test
    public void shouldGetFacultyById() throws Exception {
        Assertions.assertEquals(testFaculty, facultyRepository.findById(1));
    }

    @Test
    public void shouldGetAllFaculties() throws Exception {
        List<Faculty> expected = new ArrayList<>();
        expected.add(testFaculty);
        expected.add(otherFaculty);

        Assertions.assertEquals(expected, facultyRepository.findAll());
    }

    @Test
    public void shouldUpdteFaculty() throws Exception {
        testFaculty.setTitle("Math");
        facultyRepository.save(testFaculty);

        Assertions.assertEquals(testFaculty, facultyRepository.findById(1));
    }

    @Test
    public void shouldDeleteFacultyById() throws Exception {
        facultyRepository.deleteById(1);
        assertThat(facultyRepository.findById(1)).isNull();
    }

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }
}
