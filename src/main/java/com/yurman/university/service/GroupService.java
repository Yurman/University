package com.yurman.university.service;

import java.util.List;

import com.yurman.university.domain.Group;
import com.yurman.university.service.dto.GroupDto;

public interface GroupService {

    public Group getGroupById(int id);

    public GroupDto getGroupDtoById(int id);

    public Group addGroup(Group group);

    public GroupDto addGroup(GroupDto groupDto);

    public Group updateGroup(Group group);

    public GroupDto updateGroup(GroupDto groupDto);

    public void deleteGroup(int id);

    public void restoreGroup(int id);

    public List<Group> getAllGroups();

    public List<GroupDto> getAllGroupDto();

    public List<GroupDto> getAllUndeletedGroupDto();

}
