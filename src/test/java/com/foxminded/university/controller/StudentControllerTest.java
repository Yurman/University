package com.foxminded.university.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.foxminded.university.config.WebConfiguration;
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;

@ContextConfiguration(classes = { WebConfiguration.class })
@WebAppConfiguration
@ExtendWith(SpringExtension.class)

public class StudentControllerTest {

    @InjectMocks
    private StudentController controller;

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        List<Student> students = new ArrayList<>();
        Student testStudent = new Student();
        when(studentService.getAllStudents()).thenReturn(students);
        when(studentService.getStudentById(2)).thenReturn(testStudent);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldReturnStudentView() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/students");

        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("students.html"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("students"));
    }

    @Test
    public void shouldReturnStudentsInfoView() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/studentInfo");

        mockMvc.perform(request.param("id",
                "2")).andExpect(MockMvcResultMatchers.view().name("studentInfo.html"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
