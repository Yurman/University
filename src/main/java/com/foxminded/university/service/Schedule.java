package com.foxminded.university.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Professor;
import com.foxminded.university.domain.ScheduleItem;
import com.foxminded.university.domain.Student;

public class Schedule {
    private List<ScheduleItem> schedule;

    public List<ScheduleItem> getSchedule() {
	return schedule;
    }

    public void setSchedule(List<ScheduleItem> schedule) {
	this.schedule = schedule;
    }

    public List<ScheduleItem> getProfessorSchedule(Professor professor, LocalDate startDate, LocalDate endDate) {

	return schedule.stream()
		.filter(item -> item.getProfessor().equals(professor))
		.filter(item -> !(item.getDate().isBefore(startDate)))
		.filter(item -> !(item.getDate().isAfter(endDate)))
		.collect(Collectors.toList());
    }

    public List<ScheduleItem> getGroupSchedule(Group group, LocalDate startDate, LocalDate endDate) {

	return schedule.stream()
		.filter(item -> item.getGroups().contains(group))
		.filter(item -> !(item.getDate().isBefore(startDate)))
		.filter(item -> !(item.getDate().isAfter(endDate)))
		.collect(Collectors.toList());
    }

    public List<ScheduleItem> getStudentSchedule(Student student, LocalDate startDate, LocalDate endDate) {
	return getGroupSchedule(student.getGroup(), startDate, endDate);
    }   
}