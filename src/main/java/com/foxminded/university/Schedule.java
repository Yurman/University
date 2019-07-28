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
		for (ScheduleItem item : schedule) {
			if ((item.getProfessor().equals(professor))
					&& ((item.getDate().isAfter(startDate)) || item.getDate().isEqual(startDate))
					&& ((item.getDate().isBefore(endDate)) || item.getDate().isEqual(endDate))) {
				professorSchedule.add(item);
			}
		}

		return professorSchedule;
	}

	public List<ScheduleItem> getGroupSchedule(Group group, LocalDate startDate, LocalDate endDate) {
		List<ScheduleItem> groupSchedule = new ArrayList<ScheduleItem>();
		for (ScheduleItem item : schedule) {
			if ((item.getGroups().contains(group))
					&& ((item.getDate().isAfter(startDate)) || item.getDate().isEqual(startDate))
					&& ((item.getDate().isBefore(endDate)) || item.getDate().isEqual(endDate))) {
				groupSchedule.add(item);
			}
		}
		return groupSchedule;
	}

	public List<ScheduleItem> getStudentSchedule(Student student, LocalDate startDate, LocalDate endDate) {
		return getGroupSchedule(student.getGroup(), startDate, endDate);
	}
}
