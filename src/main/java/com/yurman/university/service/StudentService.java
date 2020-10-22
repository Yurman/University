package com.yurman.university.service;

import java.util.List;

import com.yurman.university.domain.Student;
import com.yurman.university.service.dto.StudentDto;

public interface StudentService {

    public Student getStudentById(int id);

    public StudentDto getStudentDtoById(int id);

    public Student addStudent(Student student);

    public StudentDto addStudent(StudentDto studentDto);

    public Student updateStudent(Student student);

    public StudentDto updateStudent(StudentDto studentDto);

    public void deleteStudent(int id);

    public void restoreStudent(int id);

    public List<Student> getAllStudents();

    public List<StudentDto> getAllStudentDto();

    public List<StudentDto> getAllStudentDtoByGroupId(int id);

}
