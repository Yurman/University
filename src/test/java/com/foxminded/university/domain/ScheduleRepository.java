package com.foxminded.university.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRepository {

    private static Schedule testSchedule = new Schedule();

    private static Group firstGroup = new Group();
    private static Group secondGroup = new Group();
    private static Student student = new Student();

    private static ScheduleItem firstItem = new ScheduleItem();
    private static ScheduleItem secondItem = new ScheduleItem();
    private static ScheduleItem thirdItem = new ScheduleItem();
    private static ScheduleItem fourthItem = new ScheduleItem();

    public static Schedule getTestSchedule() {
	return testSchedule;
    }

    public static void setTestSchedule(Schedule testSchedule) {
	ScheduleRepository.testSchedule = testSchedule;
    }

    public static Group getFirstGroup() {
	return firstGroup;
    }

    public static void setFirstGroup(Group firstGroup) {
	ScheduleRepository.firstGroup = firstGroup;
    }

    public static Group getSecondGroup() {
	return secondGroup;
    }

    public static void setSecondGroup(Group secondGroup) {
	ScheduleRepository.secondGroup = secondGroup;
    }

    public static Student getStudent() {
	return student;
    }

    public static void setStudent(Student student) {
	ScheduleRepository.student = student;
    }

    public static ScheduleItem getFirstItem() {
	return firstItem;
    }

    public static void setFirstItem(ScheduleItem firstItem) {
	ScheduleRepository.firstItem = firstItem;
    }

    public static ScheduleItem getSecondItem() {
	return secondItem;
    }

    public static void setSecondItem(ScheduleItem secondItem) {
	ScheduleRepository.secondItem = secondItem;
    }

    public static ScheduleItem getThirdItem() {
	return thirdItem;
    }

    public static void setThirdItem(ScheduleItem thirdItem) {
	ScheduleRepository.thirdItem = thirdItem;
    }

    public static ScheduleItem getFourthItem() {
	return fourthItem;
    }

    public static void setFourthItem(ScheduleItem fourthItem) {
	ScheduleRepository.fourthItem = fourthItem;
    }

    public static void initializeSchedule() {

	List<Student> students = new ArrayList<Student>();
	students.add(student);
	firstGroup.setStudents(students);
	secondGroup.setStudents(students);
	student.setGroup(firstGroup);
	List<Group> firstGroups = new ArrayList<Group>();
	firstGroups.add(firstGroup);
	List<Group> secondGroups = new ArrayList<Group>();
	secondGroups.add(secondGroup);

	firstItem.setDate(LocalDate.of(2019, 2, 28));
	firstItem.setProfessor(ProfessorRepository.getProfessor());
	firstItem.setGroups(firstGroups);

	secondItem.setDate(LocalDate.of(2019, 2, 24));
	secondItem.setProfessor(ProfessorRepository.getProfessor());
	secondItem.setGroups(secondGroups);

	thirdItem.setDate(LocalDate.of(2019, 2, 7));
	thirdItem.setProfessor(ProfessorRepository.getProfessor());
	thirdItem.setGroups(secondGroups);

	fourthItem.setDate(LocalDate.of(2019, 1, 31));
	fourthItem.setProfessor(ProfessorRepository.getProfessor());
	fourthItem.setGroups(secondGroups);

	List<ScheduleItem> schedule = new ArrayList<ScheduleItem>();
	schedule.add(firstItem);
	schedule.add(secondItem);
	schedule.add(thirdItem);
	schedule.add(fourthItem);
	testSchedule.setSchedule(schedule);

    }
}