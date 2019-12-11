package com.foxminded.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import com.foxminded.university.config.DataConfiguration;
import com.foxminded.university.domain.Group;
import com.foxminded.university.service.GroupRepository;

public class GroupDaoImplIT {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataConfiguration.class);
    private GroupDaoImpl groupDao = context.getBean(GroupDaoImpl.class);
    private Flyway flyway = FlywayWrapper.initializeFlyway();
    private Group testGroup = GroupRepository.getDaoTestGroup();
    private Group otherGroup = GroupRepository.getDaoTestGroup();
    private Group groupWithoutDepartment = new Group();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        FacultyDaoImpl facultyDao = context.getBean(FacultyDaoImpl.class);
        facultyDao.add(testGroup.getDepartment().getFaculty());

        DepartmentDaoImpl departmentDao = context.getBean(DepartmentDaoImpl.class);
        departmentDao.add(testGroup.getDepartment());

        groupDao.add(testGroup);
        otherGroup.setTitle("Optics");
        groupDao.add(otherGroup);
        groupWithoutDepartment.setTitle("Departmentless");
        groupWithoutDepartment.setYear(2);
        groupDao.add(groupWithoutDepartment);

    }

    @Test
    public void shouldGetGroupById() throws Exception {
        assertEquals(testGroup, groupDao.getById(1));
    }

    @Test
    public void shouldGetGroupWithoutDepartmentById() throws Exception {
        assertEquals(groupWithoutDepartment, groupDao.getById(3));
    }

    @Test
    public void shouldGetAllGroups() throws Exception {
        List<Group> expected = new ArrayList<>();
        expected.add(testGroup);
        expected.add(otherGroup);
        expected.add(groupWithoutDepartment);
        List<Group> result = groupDao.getAll();

        assertEquals(expected, result);
    }

    @Test
    public void shouldUpdteGroup() throws Exception {
        testGroup.setTitle("Math");
        groupDao.update(testGroup);

        assertEquals(testGroup, groupDao.getById(1));
    }

    @Test
    public void shouldUpdteGroupWithoutDepartment() throws Exception {
        groupWithoutDepartment.setTitle("Updated");
        groupWithoutDepartment.setDepartment(testGroup.getDepartment());
        groupDao.update(groupWithoutDepartment);

        assertEquals(groupWithoutDepartment, groupDao.getById(3));
    }

    @Test
    public void shouldDeleteGroupById() throws Exception {
        groupDao.delete(1);
        assertThrows(EmptyResultDataAccessException.class, () -> {
            groupDao.getById(1);
        });
    }

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }
}
