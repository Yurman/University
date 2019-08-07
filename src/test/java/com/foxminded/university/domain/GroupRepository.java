package com.foxminded.university.domain;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository {

    private static Group firstGroup = new Group();
    private static Group secondGroup = new Group();

    static void initializeGroupData() {
	
	StudentRepository.initializeStudentData();
	List<Group> firstGroups = new ArrayList<Group>();
	firstGroups.add(firstGroup);
	List<Group> secondGroups = new ArrayList<Group>();
	secondGroups.add(secondGroup);
    }
}