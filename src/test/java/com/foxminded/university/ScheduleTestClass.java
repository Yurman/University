package com.foxminded.university;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ScheduleTestClass {
	Schedule sched = new Schedule();
	Professor professor = new Professor();
	ScheduleItem first = new ScheduleItem();
	ScheduleItem second = new ScheduleItem();
	ScheduleItem third = new ScheduleItem();
	ScheduleItem fourth = new ScheduleItem();

	@Test
	public final void getProfessorScheduleTest() {

		first.setDate(LocalDate.of(2019, 2, 28));
		first.setProfessor(professor);

		second.setDate(LocalDate.of(2019, 2, 24));
		second.setProfessor(professor);

		third.setDate(LocalDate.of(2019, 2, 7));
		third.setProfessor(professor);

		fourth.setDate(LocalDate.of(2019, 1, 31));
		fourth.setProfessor(professor);

		List<ScheduleItem> schedule = new ArrayList<ScheduleItem>();
		schedule.add(first);
		schedule.add(second);
		schedule.add(third);
		schedule.add(fourth);
		sched.setSchedule(schedule);

		List<ScheduleItem> expected = new ArrayList<ScheduleItem>();
		expected.add(second);
		expected.add(third);

		List<ScheduleItem> result = new ArrayList<ScheduleItem>();
		result = sched.getProfessorSchedule(professor, LocalDate.of(2019, 2, 1), LocalDate.of(2019, 2, 27));
		Assert.assertEquals(expected, result);
	}

}