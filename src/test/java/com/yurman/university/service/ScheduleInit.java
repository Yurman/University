package com.yurman.university.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.yurman.university.domain.ScheduleItem;

public class ScheduleInit {

    public static Schedule getTestSchedule() {
	List<ScheduleItem> schedule = new ArrayList<>();
	Schedule testSchedule = new Schedule();
	schedule.add(getFirstItem());
	schedule.add(getSecondItem());
	schedule.add(getThirdItem());
	schedule.add(getFourthItem());
	testSchedule.setSchedule(schedule);
	return testSchedule;

    }

    public static ScheduleItem getFirstItem() {
	ScheduleItem first = new ScheduleItem();
	first.setDate(LocalDate.of(2019, 2, 28));
	first.setProfessor(ProfessorInit.getTestProfessor());
	first.setGroups(GroupInit.getTestGroups());
	return first;
    }

    public static ScheduleItem getSecondItem() {
	ScheduleItem second = new ScheduleItem();
	second.setDate(LocalDate.of(2019, 2, 24));
	second.setGroups(GroupInit.getTestGroups());
	second.setProfessor(ProfessorInit.getTestProfessor());
	return second;
    }

    public static ScheduleItem getThirdItem() {
	ScheduleItem third = new ScheduleItem();
	third.setDate(LocalDate.of(2019, 2, 7));
	third.setGroups(GroupInit.getTestGroups());
	third.setProfessor(ProfessorInit.getTestProfessor());
	return third;
    }

    public static ScheduleItem getFourthItem() {
	ScheduleItem fourth = new ScheduleItem();
	fourth.setDate(LocalDate.of(2019, 1, 31));
	fourth.setGroups(GroupInit.getTestGroups());
	fourth.setProfessor(ProfessorInit.getTestProfessor());
	return fourth;
    }
}
