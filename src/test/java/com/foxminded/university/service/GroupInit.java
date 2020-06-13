package com.foxminded.university.service;

import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.domain.Group;
import com.foxminded.university.service.dto.GroupDto;

public class GroupInit {

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

    public static Group getTestGroup() {
        Group testGroup = createFirstTestGroup();
        testGroup.setTitle("test");
        testGroup.setYear(2);
        testGroup.setDepartment(DepartmentInit.getTestDepartment());
        return testGroup;
    }

    public static GroupDto getTestGroupDto() {
        GroupDto groupDto = new GroupDto();
        groupDto.setTitle("Title");
        groupDto.setYear(1);
        groupDto.setId(1);
        return groupDto;
    }
}
