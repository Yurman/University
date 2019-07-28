package com.foxminded.university;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
	private List<ScheduleItem> schedule;

	public List<ScheduleItem> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<ScheduleItem> schedule) {
		this.schedule = schedule;
	}

	public List<ScheduleItem> getProfessorSchedule(Professor professor, LocalDate startDate, LocalDate endDate) {
		List<ScheduleItem> professorSchedule = new ArrayList<ScheduleItem>();
		return professorSchedule;
	}

	public List<ScheduleItem> getGroupSchedule(Group group, LocalDate startDate, LocalDate endDate) {
		List<ScheduleItem> groupSchedule = new ArrayList<ScheduleItem>();
		return groupSchedule;
	}

	public List<ScheduleItem> getStudentSchedule(Student student, LocalDate startDate, LocalDate endDate) {
		List<ScheduleItem> studentSchedule = new ArrayList<ScheduleItem>();
		return studentSchedule;
	}
}
