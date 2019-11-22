package com.foxminded.university.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.foxminded.university.dao.exception.DaoException;
import com.foxminded.university.domain.Group;
import com.foxminded.university.service.GroupRepository;

public class GroupDaoImplTest {

    private GroupDaoImpl groupDao = new GroupDaoImpl();
    private Flyway flyway = FlywayWrapper.initializeFlyway();
    private Group testGroup = GroupRepository.getDaoTestGroup();
    private Group otherGroup = GroupRepository.getDaoTestGroup();

    @Before
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        FacultyDaoImpl facultyDao = new FacultyDaoImpl();
        facultyDao.add(testGroup.getDepartment().getFaculty());

        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
        departmentDao.add(testGroup.getDepartment());

        groupDao.add(testGroup);
        otherGroup.setTitle("Optics");
        groupDao.add(otherGroup);
        otherGroup.setId(2);

    }

    @Test
    public void shouldGetGroupById() throws Exception {
        assertEquals(testGroup, groupDao.getById(1));
    }

    @Test
    public void shouldGetAllGroups() throws Exception {
        List<Group> expected = new ArrayList<>();
        expected.add(testGroup);
        expected.add(otherGroup);
        List<Group> result = groupDao.getAll();

        assertEquals(expected, result);
    }

    @Test
    public void shouldUpdteGroup() throws Exception {
        testGroup.setTitle("Math");
        groupDao.update(testGroup);

        assertEquals(testGroup, groupDao.getById(1));
    }

    @Test(expected = DaoException.class)
    public void shouldDeleteGroupById() throws Exception {
        groupDao.delete(1);
        groupDao.getById(1);
    }

    @After
    public void tearDown() throws Exception {
        flyway.clean();
    }
}
