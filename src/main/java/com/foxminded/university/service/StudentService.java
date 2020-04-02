package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.domain.Student;
import com.foxminded.university.service.dto.StudentDto;

public interface StudentService {

    public Student getStudentById(int id);

    public StudentDto getStudentDtoById(int id);

    public Student addStudent(Student student);

    public StudentDto addStudentDto(StudentDto studentDto);

    public Student updateStudent(Student student);

    public StudentDto updateStudentDto(StudentDto studentDto);

    public boolean deleteStudent(int id);

    public List<Student> getAllStudents();

    public List<StudentDto> getAllStudentDto();

}
