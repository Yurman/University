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
import com.foxminded.university.exception.QueryNotExecuteException;
import com.foxminded.university.service.DepartmentService;
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.dto.DepartmentDto;
import com.foxminded.university.service.dto.GroupDto;

@ContextConfiguration(classes = { WebConfiguration.class })
@WebAppConfiguration
@ExtendWith(SpringExtension.class)

public class GroupControllerTest {

    @InjectMocks
    private GroupController controller;

    private MockMvc mockMvc;

    @Mock
    private GroupService groupService;

    @Mock
    private DepartmentService departmentService;

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
    public void shouldReturnGroupView() throws Exception {
        List<GroupDto> groups = new ArrayList<>();
        when(groupService.getAllGroupDto()).thenReturn(groups);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/groups");

        mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(view().name("groups"))
                .andExpect(model().attributeExists("groups"));
    }

    @Test
    public void shouldReturnGroupViewWhenGroupWasDelete() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/groups");

        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public void shouldShowMessageOnGroupViewWhenGroupWasNotDeleted() throws Exception {
        when(groupService.deleteGroup(5)).thenThrow(new QueryNotExecuteException());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/groups");

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnGroupsInfoView() throws Exception {
        GroupDto group = new GroupDto();
        when(groupService.getGroupDto(2)).thenReturn(group);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/groupInfo");

        mockMvc.perform(request.param("id", "2"))
                .andExpect(view().name("groupInfo"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("group"));
    }

    @Test
    public void shouldReturnGroupsInfoViewWithErrorMessage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/groupInfo");
        when(groupService.getGroupDto(3)).thenThrow(new EntityNotFoundException("Error occurred"));

        mockMvc.perform(request.param("id", "3"))
                .andExpect(view().name("groupInfo"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("message"));
    }

    @Test
    public void shouldReturnAddGroupView() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/addGroup");
        List<DepartmentDto> departments = new ArrayList<>();
        when(departmentService.getAllDepartmentDto()).thenReturn(departments);

        mockMvc.perform(request)
                .andExpect(view().name("addGroup"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("departments"));
    }

    @Test
    public void shouldReturnUpdateGroupView() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/updateGroup");
        List<DepartmentDto> departments = new ArrayList<>();
        GroupDto group = new GroupDto();
        when(departmentService.getAllDepartmentDto()).thenReturn(departments);
        when(groupService.getGroupDto(2)).thenReturn(group);

        mockMvc.perform(request.param("id", "2"))
                .andExpect(view().name("updateGroup"))
                .andExpect(status().isOk())
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("departments"))
                .andExpect(model().attributeExists("group"));
    }

    @Test
    public void shouldReturnUpdateGroupViewWithErrorMessage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/updateGroup");
        when(groupService.getGroupDto(3)).thenThrow(new EntityNotFoundException("Error occurred"));

        mockMvc.perform(request.param("id", "3"))
                .andExpect(view().name("updateGroup"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("message"));
    }

}
