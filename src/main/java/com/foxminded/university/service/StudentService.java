package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.domain.Student;

public interface StudentService {

    public Student addStudent(Student student);

    public Student updateStudent(Student student);

    public boolean deleteStudent(int id);

    public List<Student> getAllStudents();

    public Student getStudentById(int id);

}
