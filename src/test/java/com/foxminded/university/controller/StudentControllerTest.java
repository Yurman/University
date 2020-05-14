package com.foxminded.university.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.StudentService;
import com.foxminded.university.service.dto.GroupDto;
import com.foxminded.university.service.dto.StudentDto;

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
        viewResolver.setPrefix("/templates/student-templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void shouldReturnStudentsView() throws Exception {
        List<StudentDto> students = new ArrayList<>();
        when(studentService.getAllStudentDto()).thenReturn(students);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/students");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("student-templates/students"))
                .andExpect(model().attributeExists("students"));
    }

    @Test
    public void shouldReturnStudentsViewWhenStudentWasDelete() throws Exception {
        List<StudentDto> students = new ArrayList<>();
        when(studentService.getAllStudentDto()).thenReturn(students);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/delete-student");

        mockMvc.perform(request.param("id", "5"))
                .andExpect(redirectedUrl("/students"))
                .andExpect(status().isFound());
    }

    @Test
    public void shouldShowMessageWhenErrorOccuredWhileDeletingStudent() throws Exception {
        List<StudentDto> students = new ArrayList<>();
        when(studentService.getAllStudentDto()).thenReturn(students);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/delete-student");

        mockMvc.perform(request.param("id", "5"))
                .andExpect(redirectedUrl("/students"))
                .andExpect(status().isFound());
    }

    @Test
    public void shouldReturnStudentInfoView() throws Exception {
        StudentDto student = new StudentDto();
        when(studentService.getStudentDtoById(2)).thenReturn(student);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/student-info");

        mockMvc.perform(request.param("id", "2"))
                .andExpect(view().name("student-templates/student-info"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("student"));
    }

    @Test
    public void shouldReturnStudentInfoViewWithErrorMessage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/student-info");
        when(studentService.getStudentById(33)).thenThrow(new EntityNotFoundException("Error occurred"));

        mockMvc.perform(request.param("id", "33"))
                .andExpect(view().name("student-templates/student-info"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnEditStudentView() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/edit-student");
        List<GroupDto> groups = new ArrayList<>();
        StudentDto student = new StudentDto();
        when(groupService.getAllGroupDto()).thenReturn(groups);
        when(studentService.getStudentDtoById(2)).thenReturn(student);

        mockMvc.perform(request.param("id", "2"))
                .andExpect(view().name("student-templates/edit-student"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("groups"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    public void shouldReturnEditStudentViewWithErrorMessage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/edit-student");
        when(studentService.getStudentById(3)).thenThrow(new EntityNotFoundException("Error occurred"));

        mockMvc.perform(request.param("id", "3"))
                .andExpect(view().name("student-templates/edit-student"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("groups"));
    }

    @Test
    public void shouldReturnViewWhenStudentWasUpdated() throws Exception {
        StudentDto studentDto = new StudentDto();
        when(studentService.updateStudent(studentDto)).thenReturn(studentDto);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/edit-student");

        mockMvc.perform(request.requestAttr("studentDto", studentDto))
                .andExpect(redirectedUrl("/students"))
                .andExpect(status().isFound());
    }

    @Test
    public void shouldReturnViewWhenStudentWasAdded() throws Exception {
        StudentDto studentDto = new StudentDto();
        when(studentService.updateStudent(studentDto)).thenReturn(studentDto);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/edit-student");

        mockMvc.perform(request.requestAttr("studentDto", studentDto))
                .andExpect(redirectedUrl("/students"))
                .andExpect(status().isFound());
    }

}
