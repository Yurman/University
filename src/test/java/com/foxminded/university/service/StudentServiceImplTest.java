package com.foxminded.university.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.impl.StudentServiceImpl;

@SpringBootTest(classes = StudentServiceImpl.class)
class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentDao studentDaoMock;

    private Student studentMock = StudentRepository.getDaoTestStudent();

    @Test
    public void getStudentByIdTest() {
        Mockito.when(studentDaoMock.getById(1)).thenReturn(studentMock);

        Student student = studentService.getStudentById(1);
        assertEquals(student, studentMock);
    }

    @Test
    public void updateStudentTest() {
        Mockito.when(studentDaoMock.update(studentMock)).thenReturn(studentMock);

        Student student = studentService.updateStudent(studentMock);
        assertEquals(student, studentMock);
    }

    @Test
    public void deleteStudentTest() {
        Mockito.when(studentDaoMock.delete(1)).thenThrow(EmptyResultDataAccessException.class);

        assertThrows(EmptyResultDataAccessException.class, () -> {
            studentService.deleteStudent(1);
        });
    }

    @Test
    public void getAllStudentsTest() {
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(studentMock);
        Mockito.when(studentDaoMock.getAll()).thenReturn(mockStudents);

        List<Student> actualStudents = studentService.getAllStudents();
        assertEquals(mockStudents, actualStudents);
    }

    @Test
    public void addStudentTest() {
        Mockito.when(studentDaoMock.add(studentMock)).thenReturn(studentMock);

        Student student = studentService.addStudent(studentMock);
        assertEquals(student, studentMock);
    }
}
