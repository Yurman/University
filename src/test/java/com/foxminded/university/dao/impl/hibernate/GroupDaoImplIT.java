package com.foxminded.university.dao.impl.hibernate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foxminded.university.config.TestDataConfiguration;
import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.domain.Group;
import com.foxminded.university.service.GroupRepository;

@ContextConfiguration(classes = { TestDataConfiguration.class })
@ExtendWith(SpringExtension.class)
public class GroupDaoImplIT {

    @Autowired
    private GroupDao groupDao;
    @Autowired
    private FacultyDao facultyDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    private Flyway flyway;
    private Group testGroup = GroupRepository.getDaoTestGroup();
    private Group otherGroup = GroupRepository.getDaoTestGroup();
    private Group groupWithoutDepartment = new Group();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        facultyDao.add(testGroup.getDepartment().getFaculty());
        departmentDao.add(testGroup.getDepartment());
        groupDao.add(testGroup);
        otherGroup.setTitle("Optics");
        otherGroup.setId(2);
        groupDao.add(otherGroup);
        groupWithoutDepartment.setTitle("Departmentless");
        groupWithoutDepartment.setYear(2);
        groupWithoutDepartment.setId(3);
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
        assertThat(groupDao.getAll()).hasSize(3).contains(testGroup, otherGroup, groupWithoutDepartment);
    }

    @Test
    public void shouldGetAllGroupsFromEmptyDB() throws Exception {
        groupDao.delete(1);
        groupDao.delete(2);
        groupDao.delete(3);
        assertThat(groupDao.getAll().isEmpty());
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
    public void shouldUpdteGroupDeletingDepartment() throws Exception {
        testGroup.setDepartment(null);
        groupDao.update(testGroup);

        assertEquals(testGroup, groupDao.getById(1));
    }

    @Test
    public void shouldDeleteGroupById() throws Exception {
        groupDao.delete(1);
        assertThat(groupDao.getAll()).hasSize(2).contains(otherGroup, groupWithoutDepartment);
    }

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }
}
