package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.domain.Student;
import com.foxminded.university.service.dto.StudentDto;

public interface StudentService {

    public Student addStudent(Student student);

    public Student updateStudent(Student student);

    public boolean deleteStudent(int id);

    public List<Student> getAllStudents();

    public Student getStudentById(int id);

    public List<StudentDto> getAllStudentDto();

    public StudentDto getStudentDto(int id);

    public Student convertDtoToStudent(StudentDto studentDto);

}
