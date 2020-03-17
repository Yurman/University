package com.foxminded.university.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.domain.Group;
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.dto.GroupDto;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;

    @Autowired
    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Group addGroup(Group group) {
        return groupDao.add(group);
    }

    @Override
    public Group updateGroup(Group group) {
        return groupDao.update(group);
    }

    @Override
    public boolean deleteGroup(int id) {
        return groupDao.delete(id);
    }

    @Override
    public Group getGroupById(int id) {
        return groupDao.getById(id);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDao.getAll();
    }

    @Override
    public GroupDto getGroupDto(int id) {
        return convertToGroupDto(groupDao.getById(id));
    }

    @Override
    public List<GroupDto> getAllGroupDto() {
        List<GroupDto> allGroupDto = new ArrayList<>();
        List<Group> groups = groupDao.getAll();
        for (Group group : groups) {
            allGroupDto.add(convertToGroupDto(group));
        }
        return allGroupDto;
    }

    private GroupDto convertToGroupDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setTitle(group.getTitle());
        groupDto.setYear(group.getYear());
        if (group.getDepartment() != null) {
            groupDto.setDepartmentTitle(group.getDepartment().getTitle());
        } else {
            groupDto.setDepartmentTitle("No department");
        }
        return groupDto;
    }
}
