package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.service.dto.StudentDto;

public interface StudentService {

    public StudentDto addStudent(StudentDto studentDto);

    public StudentDto updateStudent(StudentDto studentDto);

    public boolean deleteStudent(int id);

    public List<StudentDto> getAllStudents();

    public StudentDto getStudentById(int id);

}
