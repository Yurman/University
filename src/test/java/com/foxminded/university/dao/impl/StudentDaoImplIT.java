package com.foxminded.university.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.university.config.TestDataConfiguration;
import com.foxminded.university.dao.impl.jdbc.DepartmentDaoImpl;
import com.foxminded.university.dao.impl.jdbc.FacultyDaoImpl;
import com.foxminded.university.dao.impl.jdbc.GroupDaoImpl;
import com.foxminded.university.dao.impl.jdbc.StudentDaoImpl;
import com.foxminded.university.domain.Student;
import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.service.FlywayWrapper;
import com.foxminded.university.service.StudentRepository;

public class StudentDaoImplIT {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            TestDataConfiguration.class);
    private StudentDaoImpl studentDao = context.getBean(StudentDaoImpl.class);
    private Flyway flyway = FlywayWrapper.initializeFlyway();
    private Student testStudent = StudentRepository.getDaoTestStudent();
    private Student otherStudent = StudentRepository.getDaoTestStudent();
    private Student studentWithoutGroup = new Student();

    @BeforeEach
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();

        FacultyDaoImpl facultyDao = context.getBean(FacultyDaoImpl.class);
        facultyDao.add(testStudent.getGroup().getDepartment().getFaculty());

        DepartmentDaoImpl departmentDao = context.getBean(DepartmentDaoImpl.class);
        departmentDao.add(testStudent.getGroup().getDepartment());

        GroupDaoImpl groupDao = context.getBean(GroupDaoImpl.class);
        groupDao.add(testStudent.getGroup());
        studentDao.add(testStudent);
        otherStudent.setFirstName("Nick");
        otherStudent.setLastName("Tester");
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
        assertThat(studentDao.getAll().isEmpty());
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
