package com.foxminded.university.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.service.DepartmentInit;
import com.foxminded.university.service.FacultyInit;
import com.foxminded.university.service.GroupInit;

@SpringBootTest
public class GroupRepositoryIT {

    @Autowired
    private FacultyRepository facultyDao;

    @Autowired
    private DepartmentRepository departmentDao;

    @Autowired
    private GroupRepository groupDao;

    @Autowired
    private Flyway flyway;
    private Group testGroup = GroupInit.getTestGroup();
    private Group otherGroup = GroupInit.getTestGroup();
    private Group groupWithoutDepartment = new Group();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        Faculty faculty = FacultyInit.getTestFaculty();
        facultyDao.save(faculty);
        Department department = DepartmentInit.getTestDepartment();
        department.setFaculty(faculty);
        departmentDao.save(department);

        testGroup.setDepartment(department);
        groupDao.save(testGroup);
        otherGroup.setTitle("Optics");
        otherGroup.setDepartment(department);
        groupDao.save(otherGroup);
        groupWithoutDepartment.setTitle("Departmentless");
        groupWithoutDepartment.setYear(2);
        groupDao.save(groupWithoutDepartment);

    }

    @Test
    public void shouldGetGroupById() throws Exception {
        assertEquals(testGroup, groupDao.findById(1));
    }

    @Test
    public void shouldGetGroupWithoutDepartmentById() throws Exception {
        assertEquals(groupWithoutDepartment, groupDao.findById(3));
    }

    @Test
    public void shouldGetAllGroups() throws Exception {
        assertThat(groupDao.findAll()).hasSize(3).contains(testGroup, otherGroup, groupWithoutDepartment);
    }

    @Test
    public void shouldGetAllGroupsFromEmptyDB() throws Exception {
        groupDao.deleteById(1);
        groupDao.deleteById(2);
        groupDao.deleteById(3);
        assertThat(groupDao.findAll().isEmpty());
    }

    @Test
    public void shouldUpdteGroup() throws Exception {
        testGroup.setTitle("Math");
        groupDao.save(testGroup);

        assertEquals(testGroup, groupDao.findById(1));
    }

    @Test
    public void shouldUpdteGroupWithoutDepartment() throws Exception {
        groupWithoutDepartment.setTitle("Updated");
        groupWithoutDepartment.setDepartment(testGroup.getDepartment());
        groupDao.save(groupWithoutDepartment);

        assertEquals(groupWithoutDepartment, groupDao.findById(3));
    }

    @Test
    public void shouldUpdteGroupDeletingDepartment() throws Exception {
        testGroup.setDepartment(null);
        groupDao.save(testGroup);

        assertEquals(testGroup, groupDao.findById(1));
    }

    @Test
    public void shouldDeleteGroupById() throws Exception {
        groupDao.deleteById(1);
        assertThat(groupDao.findById(1)).isNull();
    }

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }
}
