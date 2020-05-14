package com.foxminded.university.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Professor;
import com.foxminded.university.domain.ScheduleItem;
import com.foxminded.university.domain.Student;

public class ScheduleTest {
    private static Schedule testSchedule = ScheduleInit.getTestSchedule();
    private static Professor professor = ProfessorInit.getTestProfessor();
    private static Group firstGroup = GroupInit.getFirstTestGroup();
    private static Student student = StudentInit.getTestStudent();
    private static ScheduleItem firstItem = ScheduleInit.getFirstItem();
    private static ScheduleItem secondItem = ScheduleInit.getSecondItem();
    private static ScheduleItem thirdItem = ScheduleInit.getThirdItem();

    @Test
    public final void shouldReturnScheduleForProfessorWhenCorrectDate() {
        List<ScheduleItem> result = testSchedule.getProfessorSchedule(professor, LocalDate.of(2019, 2, 1),
                LocalDate.of(2019, 2, 27));
        assertThat(result).hasSize(2).contains(secondItem, thirdItem);
    }

    @Test
    public final void shouldReturnScheduleForProfessorWhenOnEdgeDate() {
        List<ScheduleItem> result = testSchedule.getProfessorSchedule(professor, LocalDate.of(2019, 2, 24),
                LocalDate.of(2019, 2, 28));
        assertThat(result).hasSize(2).contains(firstItem, secondItem);
    }

    @Test
    public final void shouldReturnEmptyScheduleForProfessorWhenDateOutOfPeriod() {
        List<ScheduleItem> result = testSchedule.getProfessorSchedule(professor, LocalDate.of(2019, 3, 1),
                LocalDate.of(2019, 6, 27));
        assertThat(result).hasSize(0);
    }

    @Test
    public final void shouldReturnEmptyScheduleForProfessorWithoutClasses() {
        Professor otherProfessor = new Professor();
        List<ScheduleItem> result = testSchedule.getProfessorSchedule(otherProfessor, LocalDate.of(2019, 1, 1),
                LocalDate.of(2019, 1, 27));
        assertThat(result).hasSize(0);
    }

    @Test
    public final void shouldReturnScheduleForGroupWhenCorrectDate() {
        List<ScheduleItem> result = testSchedule.getGroupSchedule(firstGroup, LocalDate.of(2019, 2, 1),
                LocalDate.of(2019, 2, 27));
        assertThat(result).hasSize(2).contains(thirdItem, secondItem);
    }

    @Test
    public final void shouldReturnEmptyScheduleForGroupWhenDateOutOfPeriod() {
        List<ScheduleItem> result = testSchedule.getGroupSchedule(firstGroup, LocalDate.of(2019, 4, 30),
                LocalDate.of(2019, 9, 27));
        assertThat(result).hasSize(0);
    }

    @Test
    public final void shouldReturnScheduleForGroupWhenOnEdgeDate() {
        List<ScheduleItem> result = testSchedule.getGroupSchedule(firstGroup, LocalDate.of(2019, 2, 28),
                LocalDate.of(2019, 2, 28));
        assertThat(result).hasSize(1).contains(firstItem);
    }

    @Test
    public final void shouldReturnEmptyScheduleForGroupWithoutClasses() {
        Group otherGroup = new Group();

        List<ScheduleItem> result = testSchedule.getGroupSchedule(otherGroup, LocalDate.of(2019, 1, 1),
                LocalDate.of(2019, 9, 27));
        assertThat(result).hasSize(0);
    }

    @Test
    public final void shouldReturnScheduleForStudentWhenCorrectDate() {
        List<ScheduleItem> result = testSchedule.getStudentSchedule(student, LocalDate.of(2019, 2, 1),
                LocalDate.of(2019, 2, 27));
        assertThat(result).hasSize(2).contains(secondItem, thirdItem);
    }

    @Test
    public final void shouldReturnEmptyScheduleForStudentOutOfPeriodTest() {
        List<ScheduleItem> result = testSchedule.getStudentSchedule(student, LocalDate.of(2019, 6, 1),
                LocalDate.of(2019, 9, 27));
        assertThat(result).hasSize(0);
    }

    @Test
    public final void shouldReturnEmptyScheduleForStudentWithoutClasses() {
        Student otherStudent = new Student();

        List<ScheduleItem> result = testSchedule.getStudentSchedule(otherStudent, LocalDate.of(2019, 1, 1),
                LocalDate.of(2019, 3, 27));
        assertThat(result).hasSize(0);
    }
}
