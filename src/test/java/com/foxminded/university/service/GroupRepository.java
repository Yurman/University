package com.foxminded.university.service;

import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.domain.Group;

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
        testGroup.setTitle("test");
        testGroup.setYear(2);
        testGroup.setDepartment(DepartmentRepository.getTestDepartment());
        return testGroup;
    }
}
