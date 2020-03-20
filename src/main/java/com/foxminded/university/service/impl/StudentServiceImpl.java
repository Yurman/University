package com.foxminded.university.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;
import com.foxminded.university.service.dto.StudentDto;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student addStudent(Student student) {
        return studentDao.add(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentDao.update(student);
    }

    @Override
    public boolean deleteStudent(int id) {
        return studentDao.delete(id);
    }

    @Override
    public Student getStudentById(int id) {
        return studentDao.getById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAll();
    }

    @Override
    public StudentDto getStudentDto(int id) {
        return convertToStudentDto(studentDao.getById(id));
    }

    @Override
    public List<StudentDto> getAllStudentDto() {
        List<StudentDto> allStudentDto = new ArrayList<>();
        List<Student> students = studentDao.getAll();
        for (Student student : students) {
            allStudentDto.add(convertToStudentDto(student));
        }
        return allStudentDto;
    }

    private StudentDto convertToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        if (student.getGroup() != null) {
            studentDto.setGroupTitle(student.getGroup().getTitle());
        }
        return studentDto;
    }
}
