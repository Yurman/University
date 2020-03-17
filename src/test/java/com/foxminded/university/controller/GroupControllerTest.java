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
import com.foxminded.university.domain.Group;
import com.foxminded.university.service.GroupService;

@ContextConfiguration(classes = { WebConfiguration.class })
@WebAppConfiguration
@ExtendWith(SpringExtension.class)

public class GroupControllerTest {

    @InjectMocks
    private GroupController controller;

    private MockMvc mockMvc;

    @Mock
    private GroupService groupService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        List<Group> groups = new ArrayList<>();
        Group testGroup = new Group();
        when(groupService.getAllGroups()).thenReturn(groups);
        when(groupService.getGroupById(2)).thenReturn(testGroup);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldReturnGroupView() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/groups");

        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("groups.html"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("groups"));
    }

    @Test
    public void shouldReturnGroupsInfoView() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/groupInfo");

        mockMvc.perform(request.param("id",
                "2")).andExpect(MockMvcResultMatchers.view().name("groupInfo.html"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
