package com.foxminded.university.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.service.Schedule;

public class ScheduleRepository {

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
	first.setProfessor(ProfessorRepository.getTestProfessor());
	first.setGroups(GroupRepository.getTestGroups());
	return first;
    }

    public static ScheduleItem getSecondItem() {
	ScheduleItem second = new ScheduleItem();
	second.setDate(LocalDate.of(2019, 2, 24));
	second.setGroups(GroupRepository.getTestGroups());
	second.setProfessor(ProfessorRepository.getTestProfessor());
	return second;
    }

    public static ScheduleItem getThirdItem() {
	ScheduleItem third = new ScheduleItem();
	third.setDate(LocalDate.of(2019, 2, 7));
	third.setGroups(GroupRepository.getTestGroups());
	third.setProfessor(ProfessorRepository.getTestProfessor());
	return third;
    }

    public static ScheduleItem getFourthItem() {
	ScheduleItem fourth = new ScheduleItem();
	fourth.setDate(LocalDate.of(2019, 1, 31));
	fourth.setGroups(GroupRepository.getTestGroups());
	fourth.setProfessor(ProfessorRepository.getTestProfessor());
	return fourth;
    }
}
