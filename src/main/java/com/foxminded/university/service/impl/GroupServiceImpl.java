package com.foxminded.university.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.domain.Group;
import com.foxminded.university.service.DepartmentService;
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.dto.GroupDto;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;
    private DepartmentService departmentService;

    @Autowired
    public GroupServiceImpl(GroupDao groupDao, DepartmentService departmentService) {
        this.groupDao = groupDao;
        this.departmentService = departmentService;
    }

    @Override
    public GroupDto addGroup(GroupDto groupDto) {
        groupDao.add(convertDtoToGroup(groupDto));
        return groupDto;
    }

    @Override
    public GroupDto updateGroup(GroupDto groupDto) {
        groupDao.update(convertDtoToGroup(groupDto));
        return groupDto;
    }

    @Override
    public boolean deleteGroup(int id) {
        return groupDao.delete(id);
    }

    @Override
    public GroupDto getGroupById(int id) {
        return convertToGroupDto(groupDao.getById(id));
    }

    @Override
    public List<GroupDto> getAllGroups() {
        List<GroupDto> allGroupDto = new ArrayList<>();
        List<Group> groups = groupDao.getAll();
        for (Group group : groups) {
            allGroupDto.add(convertToGroupDto(group));
        }
        return allGroupDto;
    }

    private Group convertDtoToGroup(GroupDto groupDto) {
        Group group = (groupDto.getId() != 0) ? groupDao.getById(groupDto.getId()) : new Group();
        group.setId(groupDto.getId());
        group.setTitle(groupDto.getTitle());
        group.setYear(groupDto.getYear());
        if (groupDto.getDepartmentId() != 0) {
            group.setDepartment(departmentService.getDepartmentById(groupDto.getDepartmentId()));
        }
        return group;
    }

    private GroupDto convertToGroupDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setTitle(group.getTitle());
        groupDto.setYear(group.getYear());
        if (group.getDepartment() != null) {
            groupDto.setDepartmentTitle(group.getDepartment().getTitle());
            groupDto.setDepartmentId(group.getDepartment().getId());
        }
        return groupDto;
    }

}
