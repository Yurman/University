package com.foxminded.university.service;

import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.domain.Group;
import com.foxminded.university.service.dto.GroupDto;

public class GroupRepository {

    public static Group createFirstTestGroup() {
        Group first = new Group();
        first.setTitle("MF-11");
        return first;
    }

    public static Group getFirstTestGroup() {
        Group first = createFirstTestGroup();
        return first;
    }

    public static Group getSecondTestGroup() {
        Group second = new Group();
        second.setTitle("MF-21");
        return second;
    }

    public static List<Group> getTestGroups() {
        List<Group> testGroups = new ArrayList<>();
        testGroups.add(getFirstTestGroup());
        return testGroups;
    }

    public static Group getDaoTestGroup() {
        Group testGroup = createFirstTestGroup();
        testGroup.setId(1);
        testGroup.setDepartment(DepartmentRepository.getTestDepartment());
        return testGroup;
    }

    public static GroupDto getGroupDto() {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(1);
        groupDto.setTitle("test");
        groupDto.setYear(2);
        return groupDto;
    }
}
