package com.yurman.university.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yurman.university.domain.Group;
import com.yurman.university.repository.DepartmentRepository;
import com.yurman.university.repository.GroupRepository;
import com.yurman.university.service.GroupService;
import com.yurman.university.service.dto.GroupDto;

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
        Group group = groupRepository.findById(id);
        group.setDeleted(true);
        groupRepository.save(group);
    }

    @Override
    public void restoreGroup(int id) {
        Group group = groupRepository.findById(id);
        group.setDeleted(false);
        groupRepository.save(group);
    }

    @Override
    public Group getGroupById(int id) {
        return groupRepository.findById(id);
    }

    @Override
    public GroupDto getGroupDtoById(int id) {
        return toDto(groupRepository.findById(id));
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public List<GroupDto> getAllGroupDto() {
        return toDto(groupRepository.findAll());
    }

    @Override
    public List<GroupDto> getAllUndeletedGroupDto() {
        return toDto(groupRepository.findAllByDeleted(false));
    }

    private List<GroupDto> toDto(List<Group> groups) {
        return groups.stream().map(group -> toDto(group)).collect(Collectors.toList());
    }

    private GroupDto toDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setTitle(group.getTitle());
        groupDto.setYear(group.getYear());
        groupDto.setDeleted(group.isDeleted());
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
