package com.foxminded.university.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import com.foxminded.university.dao.impl.StudentDaoImpl;
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.impl.StudentServiceImpl;

class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentDaoImpl studentDaoMock;

    private Student expectedStudent = StudentRepository.getDaoTestStudent();

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetStudentById() {
        when(studentDaoMock.getById(1)).thenReturn(expectedStudent);

        Student actualStudent = studentService.getStudentById(1);
        assertEquals(actualStudent, expectedStudent);
    }

    @Test
    public void shouldUpdateStudent() {
        when(studentDaoMock.update(expectedStudent)).thenReturn(expectedStudent);

        Student actualStudent = studentService.updateStudent(expectedStudent);
        assertEquals(actualStudent, expectedStudent);
    }

    @Test
    public void shouldDeleteStudent() {
        when(studentDaoMock.delete(1)).thenThrow(EmptyResultDataAccessException.class);

        assertThrows(EmptyResultDataAccessException.class, () -> {
            studentService.deleteStudent(1);
        });
    }

    @Test
    public void shouldGetAllStudents() {
        List<Student> expectedStudents = new ArrayList<>();
        expectedStudents.add(expectedStudent);
        when(studentDaoMock.getAll()).thenReturn(expectedStudents);

        List<Student> actualStudents = studentService.getAllStudents();
        assertEquals(expectedStudents, actualStudents);
    }

    @Test
    public void shouldAddStudent() {
        when(studentDaoMock.add(expectedStudent)).thenReturn(expectedStudent);

        Student actualStudent = studentService.addStudent(expectedStudent);
        assertEquals(actualStudent, expectedStudent);
    }
}
