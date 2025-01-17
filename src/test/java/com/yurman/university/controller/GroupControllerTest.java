package com.yurman.university.controller;

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

import com.yurman.university.domain.Group;
import com.yurman.university.exception.EntityNotFoundException;
import com.yurman.university.service.DepartmentService;
import com.yurman.university.service.GroupService;
import com.yurman.university.service.dto.DepartmentDto;
import com.yurman.university.service.dto.GroupDto;

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
        viewResolver.setPrefix("/templates/group-templates/");
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

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("group-templates/groups"))
                .andExpect(model().attributeExists("groups"));
    }

    @Test
    public void shouldReturnViewWhenGroupWasDelete() throws Exception {
        Group group = new Group();
        when(groupService.getGroupById(5)).thenReturn(group);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/delete-group");

        mockMvc.perform(request.param("id", "5"))
                .andExpect(redirectedUrl("/groups"))
                .andExpect(status().isFound());
    }

    @Test
    public void shouldShowMessageWhenErrorOccuredWhileGroupDeleting() throws Exception {
        Group group = new Group();
        when(groupService.getGroupById(5)).thenReturn(group);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/delete-group");

        mockMvc.perform(request.param("id", "5"))
                .andExpect(redirectedUrl("/groups"))
                .andExpect(status().isFound());
    }

    @Test
    public void shouldReturnViewWhenGroupWasRestored() throws Exception {
        Group group = new Group();
        when(groupService.getGroupById(5)).thenReturn(group);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/restore-group");

        mockMvc.perform(request.param("id", "5"))
                .andExpect(redirectedUrl("/groups"))
                .andExpect(status().isFound());
    }

    @Test
    public void shouldReturnGroupsInfoView() throws Exception {
        GroupDto group = new GroupDto();
        when(groupService.getGroupDtoById(2)).thenReturn(group);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/group-info");

        mockMvc.perform(request.param("id", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("group-templates/group-info"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("group"));
    }

    @Test
    public void shouldReturnGroupsInfoViewWithErrorMessage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/group-info");
        when(groupService.getGroupDtoById(3)).thenThrow(new EntityNotFoundException("Error occurred"));

        mockMvc.perform(request.param("id", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("group-templates/group-info"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("message"));
    }

    @Test
    public void shouldReturnEditGroupView() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/edit-group");
        List<DepartmentDto> departments = new ArrayList<>();
        GroupDto group = new GroupDto();
        when(departmentService.getAllDepartmentDto()).thenReturn(departments);
        when(groupService.getGroupDtoById(2)).thenReturn(group);

        mockMvc.perform(request.param("id", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("group-templates/edit-group"))
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("departments"))
                .andExpect(model().attributeExists("group"));
    }

    @Test
    public void shouldReturnEditGroupViewWithErrorMessage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/edit-group");
        when(groupService.getGroupDtoById(3)).thenThrow(new EntityNotFoundException("Error occurred"));

        mockMvc.perform(request.param("id", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("group-templates/edit-group"))
                .andExpect(model().attributeExists("message"));
    }

    @Test
    public void shouldReturnViewWhenGroupWasUpdated() throws Exception {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(1);
        when(groupService.updateGroup(groupDto)).thenReturn(groupDto);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/edit-group");

        mockMvc.perform(request.requestAttr("groupDto", groupDto))
                .andExpect(redirectedUrl("/groups"))
                .andExpect(status().isFound());
    }

    @Test
    public void shouldReturnViewWhenGroupWasAdded() throws Exception {
        GroupDto groupDto = new GroupDto();
        when(groupService.addGroup(groupDto)).thenReturn(groupDto);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/edit-group");

        mockMvc.perform(request.requestAttr("groupDto", groupDto))
                .andExpect(redirectedUrl("/groups"))
                .andExpect(status().isFound());
    }

}
