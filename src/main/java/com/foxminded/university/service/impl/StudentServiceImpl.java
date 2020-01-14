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
        logger.debug("addStudent() [{}]", student);
        try {
            studentDao.add(student);
        } catch (EntityNotFoundException ex) {
            logger.error("addStudent() [{}]", student, ex);
        } catch (QueryNotExecuteException exc) {
            logger.error("addStudent() [{}]", student, exc);
        }
        logger.trace("Result: [{}] ", student);
        return student;
    }

    @Override
    public Student updateStudent(Student student) {
        logger.debug("updateStudent() [{}]", student);
        try {
            studentDao.update(student);
        } catch (EntityNotFoundException ex) {
            logger.error("updateStudent() [{}]", student, ex);
        } catch (QueryNotExecuteException exc) {
            logger.error("updateStudent() [{}]", student, exc);
        }
        logger.trace("Result: [{}] ", student);
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
            logger.error("deleteStudent() [{}]", id, exc);
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
            logger.error("getStudentById() [{}]", id, ex);
        } catch (QueryNotExecuteException exc) {
            logger.error("getStudentById() [{}]", id, exc);
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
