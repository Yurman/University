package com.foxminded.university.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.service.Schedule;

public class ScheduleRepository {

    private static Schedule testSchedule = new Schedule();

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

	GroupRepository.initializeGroupData();

	firstItem.setDate(LocalDate.of(2019, 2, 28));
	firstItem.setProfessor(ProfessorRepository.getProfessor());
	firstItem.setGroups(GroupRepository.getFirstGroups());

	secondItem.setDate(LocalDate.of(2019, 2, 24));
	secondItem.setProfessor(ProfessorRepository.getProfessor());
	secondItem.setGroups(GroupRepository.getSecondGroups());

	thirdItem.setDate(LocalDate.of(2019, 2, 7));
	thirdItem.setProfessor(ProfessorRepository.getProfessor());
	thirdItem.setGroups(GroupRepository.getSecondGroups());

	fourthItem.setDate(LocalDate.of(2019, 1, 31));
	fourthItem.setProfessor(ProfessorRepository.getProfessor());
	fourthItem.setGroups(GroupRepository.getSecondGroups());

	List<ScheduleItem> schedule = new ArrayList<ScheduleItem>();
	schedule.add(firstItem);
	schedule.add(secondItem);
	schedule.add(thirdItem);
	schedule.add(fourthItem);
	testSchedule.setSchedule(schedule);

    }
}
