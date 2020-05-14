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
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.DepartmentInit;
import com.foxminded.university.service.FacultyInit;
import com.foxminded.university.service.GroupInit;
import com.foxminded.university.service.StudentInit;

@SpringBootTest
public class StudentRepositoryIT {

    @Autowired
    private StudentRepository studentDao;

    @Autowired
    private FacultyRepository facultyDao;

    @Autowired
    private DepartmentRepository departmentDao;

    @Autowired
    private GroupRepository groupDao;

    @Autowired
    private Flyway flyway;
    private Student testStudent = StudentInit.getDaoTestStudent();
    private Student otherStudent = StudentInit.getDaoTestStudent();
    private Student studentWithoutGroup = new Student();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        Faculty faculty = FacultyInit.getTestFaculty();
        facultyDao.save(faculty);
        Department department = DepartmentInit.getTestDepartment();
        department.setFaculty(faculty);
        departmentDao.save(department);

        Group group = GroupInit.getTestGroup();
        group.setDepartment(department);
        groupDao.save(group);
        testStudent.setGroup(group);
        studentDao.save(testStudent);
        otherStudent.setFirstName("Nick");
        otherStudent.setLastName("Tester");
        otherStudent.setGroup(group);
        studentDao.save(otherStudent);
        studentWithoutGroup.setFirstName("Jack");
        studentWithoutGroup.setLastName("Daniels");
        studentDao.save(studentWithoutGroup);
    }

    @Test
    public void shouldGetStudentById() throws Exception {
        assertEquals(testStudent, studentDao.findById(1));
    }

    @Test
    public void shouldGetStudentWithoutGroupById() throws Exception {
        assertEquals(studentWithoutGroup, studentDao.findById(3));
    }

    @Test
    public void shouldGetAllStudents() throws Exception {
        assertThat(studentDao.findAll()).hasSize(3).contains(testStudent, otherStudent, studentWithoutGroup);
    }

    @Test
    public void shouldGetAllStudentsFromEmptyDB() throws Exception {
        studentDao.deleteById(1);
        studentDao.deleteById(2);
        studentDao.deleteById(3);
        assertThat(studentDao.findAll()).isEmpty();
    }

    @Test
    public void shouldUpdateStudent() throws Exception {
        testStudent.setFirstName("Nicolas");
        studentDao.save(testStudent);
        assertEquals(testStudent, studentDao.findById(1));
    }

    @Test
    public void shouldUpdateStudentWithoutGroup() throws Exception {
        studentWithoutGroup.setFirstName("Jonny");
        studentWithoutGroup.setLastName("Walker");
        studentWithoutGroup.setGroup(testStudent.getGroup());
        studentDao.save(studentWithoutGroup);
        assertEquals(studentWithoutGroup, studentDao.findById(3));
    }

    @Test
    public void shouldUpdateStudentDeletingGroup() throws Exception {
        testStudent.setGroup(null);
        studentDao.save(testStudent);
        assertEquals(testStudent, studentDao.findById(1));
    }

    @Test
    public void shouldDeleteStudent() throws Exception {
        studentDao.deleteById(2);
        assertThat(studentDao.findById(2)).isNull();
    }

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }
}
