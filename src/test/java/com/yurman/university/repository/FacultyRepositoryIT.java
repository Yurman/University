package com.yurman.university.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yurman.university.domain.Faculty;
import com.yurman.university.service.FacultyInit;

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
	otherFaculty.setDeleted(true);
	facultyRepository.save(otherFaculty);
	otherFaculty.setId(2);
    }

    @Test
    public void shouldGetFacultyById() throws Exception {
	assertEquals(testFaculty, facultyRepository.findById(1));
    }

    @Test
    public void shouldGetAllFaculties() throws Exception {
	List<Faculty> expected = new ArrayList<>();
	expected.add(testFaculty);
	expected.add(otherFaculty);

	assertEquals(expected, facultyRepository.findAll());
    }

    @Test
    public void shouldGetAllUndeletedFaculties() throws Exception {
	assertThat(facultyRepository.findAllByDeleted(false)).hasSize(1).contains(testFaculty);
    }

    @Test
    public void shouldGetAllDeletedFaculties() throws Exception {
	assertThat(facultyRepository.findAllByDeleted(true)).hasSize(1).contains(otherFaculty);
    }

    @Test
    public void shouldUpdteFaculty() throws Exception {
	testFaculty.setTitle("Math");
	facultyRepository.save(testFaculty);

	assertEquals(testFaculty, facultyRepository.findById(1));
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
