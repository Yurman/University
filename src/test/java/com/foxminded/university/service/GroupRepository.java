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
	first.setStudents(StudentRepository.getStudentsGroup());
	return first;
    }

    public static Group getSecondTestGroup() {
	Group second = new Group();
	second.setTitle("MF-21");
	second.setStudents(StudentRepository.getStudentsGroup());
	return second;
    }

    public static List<Group> getTestGroups() {
	List<Group> testGroups = new ArrayList<>();
	testGroups.add(getFirstTestGroup());
	return testGroups;
    }
}
