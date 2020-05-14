package com.foxminded.university.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.domain.Student;
import com.foxminded.university.repository.GroupRepository;
import com.foxminded.university.repository.StudentRepository;
import com.foxminded.university.service.StudentService;
import com.foxminded.university.service.dto.StudentDto;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentDao;
    private GroupRepository groupDao;

    @Autowired
    public StudentServiceImpl(StudentRepository studentDao, GroupRepository groupDao) {
        this.studentDao = studentDao;
        this.groupDao = groupDao;
    }

    @Override
    public Student getStudentById(int id) {
        return studentDao.findById(id);
    }

    @Override
    public StudentDto getStudentDtoById(int id) {
        return convertToStudentDto(studentDao.findById(id));
    }

    @Override
    public Student addStudent(Student student) {
        return studentDao.save(student);
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        studentDao.save(convertDtoToStudent(studentDto));
        return studentDto;
    }

    @Override
    public Student updateStudent(Student student) {
        return studentDao.save(student);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        studentDao.save(convertDtoToStudent(studentDto));
        return studentDto;
    }

    @Override
    public void deleteStudent(int id) {
        studentDao.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public List<StudentDto> getAllStudentDto() {
        List<StudentDto> allStudentDto = new ArrayList<>();
        List<Student> students = studentDao.findAll();
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
            studentDto.setGroupId(student.getGroup().getId());
        }
        return studentDto;
    }

    private Student convertDtoToStudent(StudentDto studentDto) {
        Student student = (studentDto.getId() != 0) ? studentDao.findById(studentDto.getId()) : new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        if (studentDto.getGroupId() != 0) {
            student.setGroup(groupDao.findById(studentDto.getGroupId()));
        }
        return student;
    }

}
