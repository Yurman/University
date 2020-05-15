package com.foxminded.university.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.domain.Group;
import com.foxminded.university.repository.DepartmentRepository;
import com.foxminded.university.repository.GroupRepository;
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.dto.GroupDto;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository,
            DepartmentRepository departmentRepository) {
        this.groupRepository = groupRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public GroupDto addGroup(GroupDto groupDto) {
        groupRepository.save(convertDtoToGroup(groupDto));
        return groupDto;
    }

    @Override
    public Group updateGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public GroupDto updateGroup(GroupDto groupDto) {
        groupRepository.save(convertDtoToGroup(groupDto));
        return groupDto;
    }

    @Override
    public void deleteGroup(int id) {
        groupRepository.deleteById(id);
    }

    @Override
    public Group getGroupById(int id) {
        return groupRepository.findById(id);
    }

    @Override
    public GroupDto getGroupDtoById(int id) {
        return convertToGroupDto(groupRepository.findById(id));
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public List<GroupDto> getAllGroupDto() {
        List<GroupDto> allGroupDto = new ArrayList<>();
        List<Group> groups = groupRepository.findAll();
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
        Group group = (groupDto.getId() != 0) ? groupRepository.findById(groupDto.getId()) : new Group();
        group.setTitle(groupDto.getTitle());
        group.setYear(groupDto.getYear());
        if (groupDto.getDepartmentId() != 0) {
            group.setDepartment(departmentRepository.findById(groupDto.getDepartmentId()));
        }
        return group;
    }

}
