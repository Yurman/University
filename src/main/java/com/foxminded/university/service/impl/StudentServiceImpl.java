package com.foxminded.university.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.exception.QueryNotExecuteException;
import com.foxminded.university.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student addStudent(Student student) {
        logger.debug("addStudent() [{}]", student.toString());
        try {
            studentDao.add(student);
        } catch (EntityNotFoundException ex) {
            logger.error(String.format("addStudent() [{}]", student.toString()), ex);
        } catch (QueryNotExecuteException exc) {
            logger.error(String.format("addStudent() [{}]", student.toString()), exc);
        }
        logger.trace("Result: [{}] ", student.toString());
        return student;
    }

    @Override
    public Student updateStudent(Student student) {
        logger.debug("updateStudent() [{}]", student.toString());
        try {
            studentDao.update(student);
        } catch (EntityNotFoundException ex) {
            logger.error(String.format("updateStudent() [{}]", student.toString()), ex);
        } catch (QueryNotExecuteException exc) {
            logger.error(String.format("updateStudent() [{}]", student.toString()), exc);
        }
        logger.trace("Result: [{}] ", student.toString());
        return student;
    }

    @Override
    public boolean deleteStudent(int id) {
        logger.debug("deleteStudent() [{}]", id);
        boolean isDeleted = false;
        try {
            isDeleted = studentDao.delete(id);
        } catch (EntityNotFoundException ex) {
            logger.error("deleteStudent() " + id, ex);
        } catch (QueryNotExecuteException exc) {
            logger.error(String.format("deleteStudent() [{}]", id), exc);
        }
        logger.trace("Result: [{}] ", isDeleted);
        return isDeleted;
    }

    @Override
    public Student getStudentById(int id) {
        logger.debug("getStudentById() [{}]", id);
        Student student = null;
        try {
            student = studentDao.getById(id);
        } catch (EntityNotFoundException ex) {
            logger.error(String.format("getStudentById() [{}]", id), ex);
        } catch (QueryNotExecuteException exc) {
            logger.error(String.format("getStudentById() [{}]", id), exc);
        }
        logger.trace("Result: [{}] ", id);
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        logger.debug("getAllStudents()");
        List<Student> students = null;
        try {
            students = studentDao.getAll();
        } catch (EntityNotFoundException ex) {
            logger.error("getAllStudents()", ex);
        } catch (QueryNotExecuteException exc) {
            logger.error("getAllStudents()", exc);
        }
        logger.trace("Result: [{}] ", students.size());
        return students;
    }
}
