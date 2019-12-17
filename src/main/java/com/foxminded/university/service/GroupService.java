package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.domain.Group;

public interface GroupService {

    public Group addGroup(Group group);

    public Group updateGroup(Group group);

    public Group getGroupById(int id);

    public boolean deleteGroup(int id);

    public List<Group> getAllGroups();

}
