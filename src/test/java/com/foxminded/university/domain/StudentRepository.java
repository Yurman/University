package com.foxminded.university.domain;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private static Group firstGroup = new Group();
    private static Group secondGroup = new Group();
    private static Student student = new Student();

    static void initializeStudentData() {

	List<Student> students = new ArrayList<Student>();
	students.add(student);
	firstGroup.setStudents(students);
	secondGroup.setStudents(students);
	student.setGroup(firstGroup);
    }
}