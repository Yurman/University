package com.foxminded.university.service;

import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Professor;
import com.foxminded.university.domain.ScheduleItem;
import com.foxminded.university.domain.Student;

public class ScheduleTest {

    @Test
    public final void shouldReturnScheduleForProfessorWhenCorrectDate() {
	Schedule testSchedule = ScheduleRepository.getTestSchedule();
	Professor professor = ProfessorRepository.getTestProfessor();
	ScheduleItem secondItem = ScheduleRepository.getSecondItem();
	ScheduleItem thirdItem = ScheduleRepository.getThirdItem();

	List<ScheduleItem> result = testSchedule.getProfessorSchedule(professor, LocalDate.of(2019, 2, 1),
		LocalDate.of(2019, 2, 27));
	assertThat(result).hasSize(2).contains(secondItem, thirdItem);
    }

    @Test
    public final void shouldReturnScheduleForProfessorWhenOnEdgeDate() {
	Schedule testSchedule = ScheduleRepository.getTestSchedule();
	Professor professor = ProfessorRepository.getTestProfessor();
	ScheduleItem firstItem = ScheduleRepository.getFirstItem();
	ScheduleItem secondItem = ScheduleRepository.getSecondItem();

	List<ScheduleItem> result = testSchedule.getProfessorSchedule(professor, LocalDate.of(2019, 2, 24),
		LocalDate.of(2019, 2, 28));
	assertThat(result).hasSize(2).contains(firstItem, secondItem);
    }

    @Test
    public final void shouldReturnEmptyScheduleForProfessorWhenDateOutOfPeriod() {
	Schedule testSchedule = ScheduleRepository.getTestSchedule();
	Professor professor = ProfessorRepository.getTestProfessor();

	List<ScheduleItem> result = testSchedule.getProfessorSchedule(professor, LocalDate.of(2019, 3, 1),
		LocalDate.of(2019, 6, 27));
	assertThat(result).hasSize(0);
    }

    @Test
    public final void shouldReturnEmptyScheduleForProfessorWithoutClasses() {
	Schedule testSchedule = ScheduleRepository.getTestSchedule();
	Professor otherProfessor = new Professor();

	List<ScheduleItem> result = testSchedule.getProfessorSchedule(otherProfessor, LocalDate.of(2019, 1, 1),
		LocalDate.of(2019, 1, 27));
	assertThat(result).hasSize(0);
    }

    @Test
    public final void shouldReturnScheduleForGroupWhenCorrectDate() {
	Schedule testSchedule = ScheduleRepository.getTestSchedule();
	Group firstGroup = GroupRepository.getFirstTestGroup();
	ScheduleItem thirdItem = ScheduleRepository.getThirdItem();
	ScheduleItem secondItem = ScheduleRepository.getSecondItem();

	List<ScheduleItem> result = testSchedule.getGroupSchedule(firstGroup, LocalDate.of(2019, 2, 1),
		LocalDate.of(2019, 2, 27));
	assertThat(result).hasSize(2).contains(thirdItem, secondItem);
    }

    @Test
    public final void shouldReturnEmptyScheduleForGroupWhenDateOutOfPeriod() {
	Schedule testSchedule = ScheduleRepository.getTestSchedule();
	Group firstGroup = GroupRepository.getFirstTestGroup();

	List<ScheduleItem> result = testSchedule.getGroupSchedule(firstGroup, LocalDate.of(2019, 4, 30),
		LocalDate.of(2019, 9, 27));
	assertThat(result).hasSize(0);
    }

    @Test
    public final void shouldReturnScheduleForGroupWhenOnEdgeDate() {
	Schedule testSchedule = ScheduleRepository.getTestSchedule();
	Group firstGroup = GroupRepository.getFirstTestGroup();
	ScheduleItem firstItem = ScheduleRepository.getFirstItem();

	List<ScheduleItem> result = testSchedule.getGroupSchedule(firstGroup, LocalDate.of(2019, 2, 28),
		LocalDate.of(2019, 2, 28));
	assertThat(result).hasSize(1).contains(firstItem);
    }

    @Test
    public final void shouldReturnEmptyScheduleForGroupWithoutClasses() {
	Schedule testSchedule = ScheduleRepository.getTestSchedule();
	Group otherGroup = new Group();

	List<ScheduleItem> result = testSchedule.getGroupSchedule(otherGroup, LocalDate.of(2019, 1, 1),
		LocalDate.of(2019, 9, 27));
	assertThat(result).hasSize(0);
    }

    @Test
    public final void shouldReturnScheduleForStudentWhenCorrectDate() {
	Schedule testSchedule = ScheduleRepository.getTestSchedule();
	Student student = StudentRepository.getTestStudent();
	ScheduleItem secondItem = ScheduleRepository.getSecondItem();
	ScheduleItem thirdItem = ScheduleRepository.getThirdItem();
	student.setGroup(GroupRepository.getFirstTestGroup());

	List<ScheduleItem> result = testSchedule.getStudentSchedule(student, LocalDate.of(2019, 2, 1),
		LocalDate.of(2019, 2, 27));
	assertThat(result).hasSize(2).contains(secondItem, thirdItem);
    }

    @Test
    public final void shouldReturnEmptyScheduleForStudentOutOfPeriodTest() {
	Schedule testSchedule = ScheduleRepository.getTestSchedule();
	Student student = StudentRepository.getTestStudent();

	List<ScheduleItem> result = testSchedule.getStudentSchedule(student, LocalDate.of(2019, 6, 1),
		LocalDate.of(2019, 9, 27));
	assertThat(result).hasSize(0);
    }

    @Test
    public final void shouldReturnEmptyScheduleForStudentWithoutClasses() {
	Schedule testSchedule = ScheduleRepository.getTestSchedule();
	Student otherStudent = new Student();

	List<ScheduleItem> result = testSchedule.getStudentSchedule(otherStudent, LocalDate.of(2019, 1, 1),
		LocalDate.of(2019, 3, 27));
	assertThat(result).hasSize(0);
    }
}
