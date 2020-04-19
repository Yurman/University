package com.foxminded.university.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.domain.Group;
import com.foxminded.university.service.impl.GroupServiceImpl;

class GroupServiceImplTest {

    @InjectMocks
    private GroupServiceImpl groupService;

    @Mock
    private GroupDao groupDaoMock;

    private Group expectedGroup = GroupRepository.getTestGroup();

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetGroupById() {
        when(groupDaoMock.getById(1)).thenReturn(expectedGroup);

        Group actualGroup = groupService.getGroupById(1);
        assertThat(actualGroup).isEqualTo(expectedGroup);
    }

    @Test
    public void shouldUpdateGroup() {
        when(groupDaoMock.update(expectedGroup)).thenReturn(expectedGroup);

        Group actualGroup = groupService.updateGroup(expectedGroup);
        assertThat(actualGroup).isEqualTo(expectedGroup);
    }

    @Test
    public void shouldDeleteGroup() {
        when(groupDaoMock.delete(1)).thenThrow(EmptyResultDataAccessException.class);

        assertThrows(EmptyResultDataAccessException.class, () -> {
            groupService.deleteGroup(1);
        });
    }

    @Test
    public void shouldGetAllGroups() {
        List<Group> expectedGroups = new ArrayList<>();
        expectedGroups.add(expectedGroup);
        when(groupDaoMock.getAll()).thenReturn(expectedGroups);

        List<Group> actualGroups = groupService.getAllGroups();
        assertEquals(expectedGroups, actualGroups);
    }

    @Test
    public void shouldaddGroup() {
        when(groupDaoMock.add(expectedGroup)).thenReturn(expectedGroup);

        Group actualGroup = groupService.addGroup(expectedGroup);
        assertThat(actualGroup).isEqualTo(expectedGroup);
    }
}
