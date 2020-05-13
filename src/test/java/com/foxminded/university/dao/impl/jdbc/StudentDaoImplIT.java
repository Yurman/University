package com.foxminded.university.dao.impl.jdbc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;
import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.service.DepartmentRepository;
import com.foxminded.university.service.FacultyRepository;
import com.foxminded.university.service.GroupRepository;
import com.foxminded.university.service.StudentRepository;

@SpringBootTest
public class StudentDaoImplIT {

    @Autowired
    @Qualifier("studentDaoJdbc")
    private StudentDao studentDao;

    @Autowired
    @Qualifier("facultyDaoJdbc")
    private FacultyDao facultyDao;

    @Autowired
    @Qualifier("departmentDaoJdbc")
    private DepartmentDao departmentDao;

    @Autowired
    @Qualifier("groupDaoJdbc")
    private GroupDao groupDao;

    @Autowired
    private Flyway flyway;
    private Student testStudent = StudentRepository.getDaoTestStudent();
    private Student otherStudent = StudentRepository.getDaoTestStudent();
    private Student studentWithoutGroup = new Student();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        Faculty faculty = FacultyRepository.getTestFaculty();
        facultyDao.add(faculty);
        Department department = DepartmentRepository.getTestDepartment();
        department.setFaculty(faculty);
        departmentDao.add(department);

        Group group = GroupRepository.getTestGroup();
        group.setDepartment(department);
        groupDao.add(group);
        testStudent.setGroup(group);
        studentDao.add(testStudent);
        otherStudent.setFirstName("Nick");
        otherStudent.setLastName("Tester");
        otherStudent.setGroup(group);
        studentDao.add(otherStudent);
        studentWithoutGroup.setFirstName("Jack");
        studentWithoutGroup.setLastName("Daniels");
        studentDao.add(studentWithoutGroup);
    }

    @Test
    public void shouldGetStudentById() throws Exception {
        assertEquals(testStudent, studentDao.getById(1));
    }

    @Test
    public void shouldGetStudentWithoutGroupById() throws Exception {
        assertEquals(studentWithoutGroup, studentDao.getById(3));
    }

    @Test
    public void shouldGetAllStudents() throws Exception {
        assertThat(studentDao.getAll()).hasSize(3).contains(testStudent, otherStudent, studentWithoutGroup);
    }

    @Test
    public void shouldGetAllStudentsFromEmptyDB() throws Exception {
        studentDao.delete(1);
        studentDao.delete(2);
        studentDao.delete(3);
        assertThat(studentDao.getAll()).isEmpty();
    }

    @Test
    public void shouldUpdateStudent() throws Exception {
        testStudent.setFirstName("Nicolas");
        studentDao.update(testStudent);
        assertEquals(testStudent, studentDao.getById(1));
    }

    @Test
    public void shouldUpdateStudentWithoutGroup() throws Exception {
        studentWithoutGroup.setFirstName("Jonny");
        studentWithoutGroup.setLastName("Walker");
        studentWithoutGroup.setGroup(testStudent.getGroup());
        studentDao.update(studentWithoutGroup);
        assertEquals(studentWithoutGroup, studentDao.getById(3));
    }

    @Test
    public void shouldUpdateStudentDeletingGroup() throws Exception {
        testStudent.setGroup(null);
        studentDao.update(testStudent);
        assertEquals(testStudent, studentDao.getById(1));
    }

    @Test
    public void shouldDeleteStudent() throws Exception {
        studentDao.delete(2);
        assertThrows(EntityNotFoundException.class, () -> {
            studentDao.getById(2);
        });
    }

    @AfterEach
    public void tearDown() throws Exception {
        flyway.clean();
    }
}
