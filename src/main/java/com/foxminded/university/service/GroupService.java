package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.domain.Group;
import com.foxminded.university.service.dto.GroupDto;

public interface GroupService {

    public Group getGroupById(int id);

    public GroupDto getGroupDtoById(int id);

    public Group addGroup(Group group);

    public GroupDto addGroup(GroupDto groupDto);

    public Group updateGroup(Group group);

    public GroupDto updateGroup(GroupDto groupDto);

    public boolean deleteGroup(int id);

    public List<Group> getAllGroups();

    public List<GroupDto> getAllGroupDto();

}
