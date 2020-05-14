package com.foxminded.university.service;

import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.domain.Student;

public class StudentInit {

    public static Student getTestStudent() {
        Student testStudent = new Student();
        testStudent.setFirstName("Petr");
        testStudent.setLastName("Ivanov");
        testStudent.setGroup(GroupInit.createFirstTestGroup());
        return testStudent;
    }

    public static List<Student> getStudentsGroup() {
        List<Student> studentsGroup = new ArrayList<>();
        studentsGroup.add(getTestStudent());
        return studentsGroup;
    }

    public static Student getDaoTestStudent() {
        Student testStudent = getTestStudent();
        testStudent.setGroup(GroupInit.getTestGroup());
        return testStudent;
    }
}
