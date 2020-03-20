package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.domain.Group;
import com.foxminded.university.service.dto.GroupDto;

public interface GroupService {

    public Group addGroup(Group group);

    public Group updateGroup(Group group);

    public Group getGroupById(int id);

    public boolean deleteGroup(int id);

    public List<Group> getAllGroups();

    public List<GroupDto> getAllGroupDto();

    public GroupDto getGroupDto(int id);

}
