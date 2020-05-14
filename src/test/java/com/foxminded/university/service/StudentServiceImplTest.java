package com.foxminded.university.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxminded.university.domain.Student;
import com.foxminded.university.repository.StudentRepository;
import com.foxminded.university.service.impl.StudentServiceImpl;

class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentDaoMock;

    private Student expectedStudent = StudentInit.getDaoTestStudent();

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetStudentById() {
        when(studentDaoMock.findById(1)).thenReturn(expectedStudent);

        Student actualStudent = studentService.getStudentById(1);
        assertThat(actualStudent).isEqualTo(expectedStudent);
    }

    @Test
    public void shouldUpdateStudent() {
        when(studentDaoMock.save(expectedStudent)).thenReturn(expectedStudent);

        Student actualStudent = studentService.updateStudent(expectedStudent);
        assertThat(actualStudent).isEqualTo(expectedStudent);
    }

    @Test
    public void shouldGetAllStudents() {
        List<Student> expectedStudents = new ArrayList<>();
        expectedStudents.add(expectedStudent);
        when(studentDaoMock.findAll()).thenReturn(expectedStudents);

        List<Student> actualStudents = studentService.getAllStudents();
        assertEquals(expectedStudents, actualStudents);
    }

    @Test
    public void shouldAddStudent() {
        when(studentDaoMock.save(expectedStudent)).thenReturn(expectedStudent);

        Student actualStudent = studentService.addStudent(expectedStudent);
        assertEquals(actualStudent, expectedStudent);
    }
}
