package com.foxminded.university.domain;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private static Student student = new Student();
    private static List<Student> students = new ArrayList<Student>();

    public static Student getStudent() {
	return student;
    }

    public static void setStudent(Student student) {
	StudentRepository.student = student;
    }

    public static List<Student> getStudents() {
	return students;
    }

    public static void setStudents(List<Student> students) {
	StudentRepository.students = students;
    }

    static void initializeStudentData() {

	students.add(student);
	student.setGroup(GroupRepository.getFirst());
    }
}