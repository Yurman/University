package com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;

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
}
