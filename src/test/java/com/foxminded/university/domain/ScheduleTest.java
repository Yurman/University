package com.foxminded.university.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class ScheduleTest {

    private static Schedule testSchedule = new Schedule();
    private static Professor professor = new Professor();
    private static Group firstGroup = new Group();
    private static Group secondGroup = new Group();
    private static Student student = new Student();

    private static ScheduleItem firstItem = new ScheduleItem();
    private static ScheduleItem secondItem = new ScheduleItem();
    private static ScheduleItem thirdItem = new ScheduleItem();
    private static ScheduleItem fourthItem = new ScheduleItem();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

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
	firstItem.setProfessor(professor);
	firstItem.setGroups(firstGroups);

	secondItem.setDate(LocalDate.of(2019, 2, 24));
	secondItem.setProfessor(professor);
	secondItem.setGroups(secondGroups);

	thirdItem.setDate(LocalDate.of(2019, 2, 7));
	thirdItem.setProfessor(professor);
	thirdItem.setGroups(secondGroups);

	fourthItem.setDate(LocalDate.of(2019, 1, 31));
	fourthItem.setProfessor(professor);
	fourthItem.setGroups(secondGroups);

	List<ScheduleItem> schedule = new ArrayList<ScheduleItem>();
	schedule.add(firstItem);
	schedule.add(secondItem);
	schedule.add(thirdItem);
	schedule.add(fourthItem);
	testSchedule.setSchedule(schedule);

    }

    @Test
    public final void getProfessorScheduleTest() {
	List<ScheduleItem> result = testSchedule.getProfessorSchedule(professor, LocalDate.of(2019, 2, 1),
		LocalDate.of(2019, 2, 27));
	assertThat(result).hasSize(2).contains(secondItem, thirdItem);
    }

    @Test
    public final void getProfessorScheduleOnEdgeDateTest() {
	List<ScheduleItem> result = testSchedule.getProfessorSchedule(professor, LocalDate.of(2019, 2, 24),
		LocalDate.of(2019, 2, 28));
	assertThat(result).hasSize(2).contains(firstItem, secondItem);
    }

    @Test
    public final void getProfessorScheduleOutOfPeriodTest() {
	List<ScheduleItem> result = testSchedule.getProfessorSchedule(professor, LocalDate.of(2019, 3, 1),
		LocalDate.of(2019, 6, 27));
	assertThat(result).hasSize(0);
    }

    @Test
    public final void getOtherProfessorScheduleTest() {
	Professor otherProfessor = new Professor();
	List<ScheduleItem> result = testSchedule.getProfessorSchedule(otherProfessor, LocalDate.of(2019, 1, 1),
		LocalDate.of(2019, 1, 27));
	assertThat(result).hasSize(0);
    }

    @Test
    public final void getGroupScheduleTest() {
	List<ScheduleItem> result = testSchedule.getGroupSchedule(firstGroup, LocalDate.of(2019, 1, 1),
		LocalDate.of(2019, 3, 27));
	assertThat(result).hasSize(1).contains(firstItem);
    }

    @Test
    public final void getGroupScheduleOutOfPeriodTest() {
	List<ScheduleItem> result = testSchedule.getGroupSchedule(firstGroup, LocalDate.of(2019, 4, 30),
		LocalDate.of(2019, 9, 27));
	assertThat(result).hasSize(0);
    }

    @Test
    public final void getGroupScheduleOnEdgeDateTest() {
	List<ScheduleItem> result = testSchedule.getGroupSchedule(firstGroup, LocalDate.of(2019, 2, 28),
		LocalDate.of(2019, 2, 28));
	assertThat(result).hasSize(1).contains(firstItem);
    }

    @Test
    public final void getOterGroupScheduleTest() {
	Group otherGroup = new Group();
	List<ScheduleItem> result = testSchedule.getGroupSchedule(otherGroup, LocalDate.of(2019, 1, 1),
		LocalDate.of(2019, 9, 27));
	assertThat(result).hasSize(0);
    }

    @Test
    public final void getStudentScheduleTest() {
	List<ScheduleItem> result = testSchedule.getStudentSchedule(student, LocalDate.of(2019, 1, 1),
		LocalDate.of(2019, 3, 27));
	assertThat(result).hasSize(1).contains(firstItem);
    }

    @Test
    public final void getStudentScheduleOutOfPeriodTest() {
	List<ScheduleItem> result = testSchedule.getStudentSchedule(student, LocalDate.of(2019, 6, 1),
		LocalDate.of(2019, 9, 27));
	assertThat(result).hasSize(0);
    }

    @Test
    public final void getOtherStudentScheduleTest() {
	Student otherStudent = new Student();
	List<ScheduleItem> result = testSchedule.getStudentSchedule(otherStudent, LocalDate.of(2019, 6, 1),
		LocalDate.of(2019, 9, 27));
	assertThat(result).hasSize(0);
    }
}