package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.service.dto.GroupDto;

public interface GroupService {

    public GroupDto addGroup(GroupDto groupDto);

    public GroupDto updateGroup(GroupDto groupDto);

    public boolean deleteGroup(int id);

    public List<GroupDto> getAllGroups();

    public GroupDto getGroupById(int id);

}
