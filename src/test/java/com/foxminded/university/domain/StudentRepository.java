package com.foxminded.university.domain;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public static Student getTestStudent() {
	Student testStudent = new Student();
	testStudent.setAge(20);
	testStudent.setFirstName("Petr");
	testStudent.setLastName("Ivanov");
	return testStudent;
    }

    public static List<Student> getStudentsGroup() {
	List<Student> studentsGroup = new ArrayList<>();
	studentsGroup.add(getTestStudent());
	return studentsGroup;
    }

}
