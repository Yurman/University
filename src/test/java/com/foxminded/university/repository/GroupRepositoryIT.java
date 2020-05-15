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
    private FacultyRepository facultyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private GroupRepository groupRepository;

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
        facultyRepository.save(faculty);
        Department department = DepartmentInit.getTestDepartment();
        department.setFaculty(faculty);
        departmentRepository.save(department);

        testGroup.setDepartment(department);
        groupRepository.save(testGroup);
        otherGroup.setTitle("Optics");
        otherGroup.setDepartment(department);
        groupRepository.save(otherGroup);
        groupWithoutDepartment.setTitle("Departmentless");
        groupWithoutDepartment.setYear(2);
        groupRepository.save(groupWithoutDepartment);

    }

    @Test
    public void shouldGetGroupById() throws Exception {
        assertEquals(testGroup, groupRepository.findById(1));
    }

    @Test
    public void shouldGetGroupWithoutDepartmentById() throws Exception {
        assertEquals(groupWithoutDepartment, groupRepository.findById(3));
    }

    @Test
    public void shouldGetAllGroups() throws Exception {
        assertThat(groupRepository.findAll()).hasSize(3).contains(testGroup, otherGroup, groupWithoutDepartment);
    }

    @Test
    public void shouldGetAllGroupsFromEmptyDB() throws Exception {
        groupRepository.deleteById(1);
        groupRepository.deleteById(2);
        groupRepository.deleteById(3);
        assertThat(groupRepository.findAll()).isEmpty();
    }

    @Test
    public void shouldUpdteGroup() throws Exception {
        testGroup.setTitle("Math");
        groupRepository.save(testGroup);

        assertEquals(testGroup, groupRepository.findById(1));
    }

    @Test
    public void shouldUpdteGroupWithoutDepartment() throws Exception {
        groupWithoutDepartment.setTitle("Updated");
        groupWithoutDepartment.setDepartment(testGroup.getDepartment());
        groupRepository.save(groupWithoutDepartment);

        assertEquals(groupWithoutDepartment, groupRepository.findById(3));
    }

    @Test
    public void shouldUpdteGroupDeletingDepartment() throws Exception {
        testGroup.setDepartment(null);
        groupRepository.save(testGroup);

        assertEquals(testGroup, groupRepository.findById(1));
    }

    @Test
    public void shouldDeleteGroupById() throws Exception {
        groupRepository.deleteById(1);
        assertThat(groupRepository.findById(1)).isNull();
    }

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }
}
