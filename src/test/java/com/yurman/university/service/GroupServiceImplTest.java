package com.yurman.university.service;

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

import com.yurman.university.domain.Group;
import com.yurman.university.repository.GroupRepository;
import com.yurman.university.service.impl.GroupServiceImpl;

class GroupServiceImplTest {

    @InjectMocks
    private GroupServiceImpl groupService;

    @Mock
    private GroupRepository groupRepositoryMock;

    private Group expectedGroup = GroupInit.getTestGroup();

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetGroupById() {
        when(groupRepositoryMock.findById(1)).thenReturn(expectedGroup);

        Group actualGroup = groupService.getGroupById(1);
        assertThat(actualGroup).isEqualTo(expectedGroup);
    }

    @Test
    public void shouldUpdateGroup() {
        when(groupRepositoryMock.save(expectedGroup)).thenReturn(expectedGroup);

        Group actualGroup = groupService.updateGroup(expectedGroup);
        assertThat(actualGroup).isEqualTo(expectedGroup);
    }

    @Test
    public void shouldGetAllGroups() {
        List<Group> expectedGroups = new ArrayList<>();
        expectedGroups.add(expectedGroup);
        when(groupRepositoryMock.findAll()).thenReturn(expectedGroups);

        List<Group> actualGroups = groupService.getAllGroups();
        assertEquals(expectedGroups, actualGroups);
    }

    @Test
    public void shouldaddGroup() {
        when(groupRepositoryMock.save(expectedGroup)).thenReturn(expectedGroup);

        Group actualGroup = groupService.addGroup(expectedGroup);
        assertThat(actualGroup).isEqualTo(expectedGroup);
    }
}
