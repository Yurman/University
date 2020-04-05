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
    public Group addGroup(Group group) {
        return groupDao.add(group);
    }

    @Override
    public GroupDto addGroupDto(GroupDto groupDto) {
        groupDao.add(convertDtoToGroup(groupDto));
        return groupDto;
    }

    @Override
    public Group updateGroup(Group group) {
        return groupDao.update(group);
    }

    @Override
    public GroupDto updateGroupDto(GroupDto groupDto) {
        groupDao.update(convertDtoToGroup(groupDto));
        return groupDto;
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
    public GroupDto getGroupDtoById(int id) {
        return convertToGroupDto(groupDao.getById(id));
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDao.getAll();
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
            groupDto.setDepartmentId(group.getDepartment().getId());
        }
        return groupDto;
    }

    private Group convertDtoToGroup(GroupDto groupDto) {
        Group group = (groupDto.getId() != 0) ? groupDao.getById(groupDto.getId()) : new Group();
        group.setTitle(groupDto.getTitle());
        group.setYear(groupDto.getYear());
        if (groupDto.getDepartmentId() != 0) {
            group.setDepartment(departmentService.getDepartmentById(groupDto.getDepartmentId()));
        }
        return group;
    }

}
