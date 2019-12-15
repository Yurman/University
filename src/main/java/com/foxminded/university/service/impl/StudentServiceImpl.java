package com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.foxminded.university.config.DataConfiguration;
import com.foxminded.university.dao.impl.StudentDaoImpl;
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;

@Component
public class StudentServiceImpl implements StudentService {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            DataConfiguration.class);
    private StudentDaoImpl studentDao = context.getBean(StudentDaoImpl.class);

    public Student addStudent(Student student) {
        return studentDao.add(student);
    }

    public Student updateStudent(Student student) {
        return studentDao.update(student);
    }

    public boolean deleteStudent(Student student) {
        return studentDao.delete(student.getId());
    }

    public Student getStudentById(Student student) {
        return studentDao.getById(student.getId());
    }

    public List<Student> getAllStudents() {
        return studentDao.getAll();
    }
}
