package com.foxminded.university.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;
import com.foxminded.university.service.dto.StudentDto;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private GroupDao groupDao;

    @Autowired
    public StudentServiceImpl(@Qualifier("studentDaoHibernate") StudentDao studentDao,
            @Qualifier("groupDaoHibernate") GroupDao groupDao) {
        this.studentDao = studentDao;
        this.groupDao = groupDao;
    }

    @Override
    @Transactional
    public Student getStudentById(int id) {
        return studentDao.getById(id);
    }

    @Override
    @Transactional
    public StudentDto getStudentDtoById(int id) {
        return convertToStudentDto(studentDao.getById(id));
    }

    @Override
    @Transactional
    public Student addStudent(Student student) {
        return studentDao.add(student);
    }

    @Override
    @Transactional
    public StudentDto addStudent(StudentDto studentDto) {
        studentDao.add(convertDtoToStudent(studentDto));
        return studentDto;
    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {
        return studentDao.update(student);
    }

    @Override
    @Transactional
    public StudentDto updateStudent(StudentDto studentDto) {
        studentDao.update(convertDtoToStudent(studentDto));
        return studentDto;
    }

    @Override
    @Transactional
    public boolean deleteStudent(int id) {
        return studentDao.delete(id);
    }

    @Override
    @Transactional
    public List<Student> getAllStudents() {
        return studentDao.getAll();
    }

    @Override
    @Transactional
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
            studentDto.setGroupId(student.getGroup().getId());
        }
        return studentDto;
    }

    private Student convertDtoToStudent(StudentDto studentDto) {
        Student student = (studentDto.getId() != 0) ? studentDao.getById(studentDto.getId()) : new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        if (studentDto.getGroupId() != 0) {
            student.setGroup(groupDao.getById(studentDto.getGroupId()));
        }
        return student;
    }

}
