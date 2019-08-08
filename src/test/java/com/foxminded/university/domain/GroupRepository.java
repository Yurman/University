package com.foxminded.university.domain;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository {

    private static Group first = new Group();
    private static Group second = new Group();
    private static List<Group> firstGroups = new ArrayList<Group>();
    private static List<Group> secondGroups = new ArrayList<Group>();

    public static List<Group> getFirstGroups() {
	return firstGroups;
    }

    public static void setFirstGroups(List<Group> firstGroups) {
	GroupRepository.firstGroups = firstGroups;
    }

    public static Group getFirst() {
	return first;
    }

    public static void setFirst(Group first) {
	GroupRepository.first = first;
    }

    public static Group getSecond() {
	return second;
    }

    public static void setSecond(Group second) {
	GroupRepository.second = second;
    }

    public static List<Group> getSecondGroups() {
	return secondGroups;
    }

    public static void setSecondGroups(List<Group> secondGroups) {
	GroupRepository.secondGroups = secondGroups;
    }

    static void initializeGroupData() {

	StudentRepository.initializeStudentData();

	first.setStudents(StudentRepository.getStudents());
	second.setStudents(StudentRepository.getStudents());

	firstGroups.add(first);

	secondGroups.add(second);
    }
}