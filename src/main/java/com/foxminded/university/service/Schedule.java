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

    @Override
    public int hashCode() {
	final int prime = 73;
	int result = 93;
	result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Schedule other = (Schedule) obj;
	if (schedule == null) {
	    if (other.schedule != null)
		return false;
	} else if (!schedule.equals(other.schedule))
	    return false;
	return true;
    }
}
