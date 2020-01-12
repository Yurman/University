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
        logger.debug("Start addStudent()" + student.toString());
        try {
            studentDao.add(student);
        } catch (EntityNotFoundException ex) {
            logger.error("Error while adding " + student.toString(), ex);
        } catch (QueryNotExecuteException exc) {
            logger.error("Error while adding " + student.toString(), exc);
        }
        logger.trace("New student " + student.toString() + " was added");
        return student;
    }

    @Override
    public Student updateStudent(Student student) {
        logger.debug("Start updateStudent()" + student.toString());
        try {
            studentDao.update(student);
        } catch (EntityNotFoundException ex) {
            logger.error("Error while updating " + student.toString(), ex);
        } catch (QueryNotExecuteException exc) {
            logger.error("Error while updating " + student.toString(), exc);
        }
        logger.trace("Student " + student.toString() + " was updated");
        return student;
    }

    @Override
    public boolean deleteStudent(int id) {
        logger.debug("Start deleteStudent() with id = " + id);
        boolean isDeleted = false;
        try {
            isDeleted = studentDao.delete(id);
        } catch (EntityNotFoundException ex) {
            logger.error("Error while deleting student with id = " + id, ex);
        } catch (QueryNotExecuteException exc) {
            logger.error("Error while deleting student with id = " + id, exc);
        }
        logger.trace("Student with id = " + id + " was deleted");
        return isDeleted;
    }

    @Override
    public Student getStudentById(int id) {
        logger.debug("Start getStudentById() with id = " + id);
        Student student = null;
        try {
            student = studentDao.getById(id);
        } catch (EntityNotFoundException ex) {
            logger.error("Error while extraction student with id = " + id, ex);
        } catch (QueryNotExecuteException exc) {
            logger.error("Error while extraction student with id = " + id, exc);
        }
        logger.trace(student.toString() + " was found");
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        logger.debug("Start getAllStudents()");
        List<Student> students = null;
        try {
            students = studentDao.getAll();
        } catch (EntityNotFoundException ex) {
            logger.error("Error while extraction students", ex);
        } catch (QueryNotExecuteException exc) {
            logger.error("Error while extraction students", exc);
        }
        logger.trace(students.size() + " students were found");
        return students;
    }
}
