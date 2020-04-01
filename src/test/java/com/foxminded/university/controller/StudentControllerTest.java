package com.foxminded.university.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.foxminded.university.config.WebConfiguration;
import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.StudentService;
import com.foxminded.university.service.dto.GroupDto;
import com.foxminded.university.service.dto.StudentDto;

@ContextConfiguration(classes = { WebConfiguration.class })
@WebAppConfiguration
@ExtendWith(SpringExtension.class)

public class StudentControllerTest {

    @InjectMocks
    private StudentController controller;

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @Mock
    private GroupService groupService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void shouldReturnStudentView() throws Exception {
        List<StudentDto> students = new ArrayList<>();
        when(studentService.getAllStudents()).thenReturn(students);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/students");

        mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(view().name("students"))
                .andExpect(model().attributeExists("students"));
    }

    @Test
    public void shouldReturnStudentsViewWhenStudentWasDelete() throws Exception {
        List<StudentDto> students = new ArrayList<>();
        when(studentService.getAllStudents()).thenReturn(students);
        when(studentService.deleteStudent(5)).thenReturn(true);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/delete-student");

        mockMvc.perform(request.param("id", "5"))
                .andExpect(view().name("students"))
                .andExpect(status().isOk())
                .andExpect(model().size(3))
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attributeExists("students"));
    }

    @Test
    public void shouldShowMessageOnStudentsViewWhenErrorWhileDeletingStudent() throws Exception {
        List<StudentDto> students = new ArrayList<>();
        when(studentService.deleteStudent(5)).thenThrow(new EntityNotFoundException("Error occurred"));
        when(studentService.getAllStudents()).thenReturn(students);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/delete-student");

        mockMvc.perform(request.param("id", "5"))
                .andExpect(view().name("students"))
                .andExpect(status().isOk())
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("message"));
    }

    @Test
    public void shouldReturnStudentInfoView() throws Exception {
        StudentDto student = new StudentDto();
        when(studentService.getStudentById(2)).thenReturn(student);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/student-info");

        mockMvc.perform(request.param("id", "2"))
                .andExpect(view().name("student-info"))
                .andExpect(status().isOk())
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    public void shouldReturnStudentInfoViewWithErrorMessage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/student-info");
        when(studentService.getStudentById(33)).thenThrow(new EntityNotFoundException("Error occurred"));

        mockMvc.perform(request.param("id", "33"))
                .andExpect(view().name("student-info"))
                .andExpect(status().isOk())
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("message"));
    }

    @Test
    public void shouldReturnEditStudentView() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/edit-student");
        List<GroupDto> groups = new ArrayList<>();
        StudentDto student = new StudentDto();
        when(groupService.getAllGroups()).thenReturn(groups);
        when(studentService.getStudentById(2)).thenReturn(student);

        mockMvc.perform(request.param("id", "2"))
                .andExpect(view().name("edit-student"))
                .andExpect(status().isOk())
                .andExpect(model().size(3))
                .andExpect(model().attributeExists("groups"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    public void shouldReturnEditStudentViewWithErrorMessage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/edit-student");
        when(studentService.getStudentById(3)).thenThrow(new EntityNotFoundException("Error occurred"));

        mockMvc.perform(request.param("id", "3"))
                .andExpect(view().name("edit-student"))
                .andExpect(status().isOk())
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("message"));
    }

}
