package com.yurman.university.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yurman.university.domain.Department;
import com.yurman.university.domain.Faculty;
import com.yurman.university.domain.Group;
import com.yurman.university.domain.Student;
import com.yurman.university.service.DepartmentInit;
import com.yurman.university.service.FacultyInit;
import com.yurman.university.service.GroupInit;
import com.yurman.university.service.StudentInit;

@SpringBootTest
public class StudentRepositoryIT {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private GroupRepository groupRepository;

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
	facultyRepository.save(faculty);
	Department department = DepartmentInit.getTestDepartment();
	department.setFaculty(faculty);
	departmentRepository.save(department);

	Group group = GroupInit.getTestGroup();
	group.setDepartment(department);
	groupRepository.save(group);
	testStudent.setGroup(group);
	studentRepository.save(testStudent);
	otherStudent.setFirstName("Nick");
	otherStudent.setLastName("Tester");
	otherStudent.setGroup(group);
	studentRepository.save(otherStudent);
	studentWithoutGroup.setFirstName("Jack");
	studentWithoutGroup.setLastName("Daniels");
	studentRepository.save(studentWithoutGroup);
    }

    @Test
    public void shouldGetStudentById() throws Exception {
	assertEquals(testStudent, studentRepository.findById(1));
    }

    @Test
    public void shouldGetStudentWithoutGroupById() throws Exception {
	assertEquals(studentWithoutGroup, studentRepository.findById(3));
    }

    @Test
    public void shouldGetAllStudents() throws Exception {
	assertThat(studentRepository.findAll()).hasSize(3).contains(testStudent, otherStudent, studentWithoutGroup);
    }

    @Test
    public void shouldGetAllStudentsFromEmptyDB() throws Exception {
	studentRepository.deleteById(1);
	studentRepository.deleteById(2);
	studentRepository.deleteById(3);
	assertThat(studentRepository.findAll()).isEmpty();
    }

    @Test
    public void shouldUpdateStudent() throws Exception {
	testStudent.setFirstName("Nicolas");
	studentRepository.save(testStudent);
	assertEquals(testStudent, studentRepository.findById(1));
    }

    @Test
    public void shouldUpdateStudentWithoutGroup() throws Exception {
	studentWithoutGroup.setFirstName("Jonny");
	studentWithoutGroup.setLastName("Walker");
	studentWithoutGroup.setGroup(testStudent.getGroup());
	studentRepository.save(studentWithoutGroup);
	assertEquals(studentWithoutGroup, studentRepository.findById(3));
    }

    @Test
    public void shouldUpdateStudentDeletingGroup() throws Exception {
	testStudent.setGroup(null);
	studentRepository.save(testStudent);
	assertEquals(testStudent, studentRepository.findById(1));
    }

    @Test
    public void shouldDeleteStudent() throws Exception {
	studentRepository.deleteById(2);
	assertThat(studentRepository.findById(2)).isNull();
    }

    @AfterEach
    public void tearDown() throws Exception {
	flyway.clean();
    }
}
