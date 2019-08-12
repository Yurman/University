package com.foxminded.university.domain;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository {

    public static Group createFirstTestGroup() {
	Group first = new Group();
	return first;
    }

    public static Group getFirstTestGroup() {
	Group first = createFirstTestGroup();
	first.setStudents(StudentRepository.getStudentsGroup());
	return first;
    }

    public static Group getSecondTestGroup() {
	Group second = new Group();
	second.setStudents(StudentRepository.getStudentsGroup());
	return second;
    }

    public static List<Group> getTestGroups() {
	List<Group> testGroups = new ArrayList<>();
	testGroups.add(getFirstTestGroup());
	return testGroups;
    }

}
