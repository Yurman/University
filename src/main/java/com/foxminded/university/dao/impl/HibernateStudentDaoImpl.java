package com.foxminded.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.HibernateSessionFactory;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import com.foxminded.university.exception.QueryNotExecuteException;

@Repository
public class HibernateStudentDaoImpl implements StudentDao {

    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private static final Logger logger = LoggerFactory.getLogger(HibernateStudentDaoImpl.class);

    @Override
    public Student getById(int id) {
        Student student = null;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            student = session.get(Student.class, id);
            transaction.commit();
            session.close();
        } catch (HibernateException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [{}] ", student);
        return student;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            students = sessionFactory.openSession()
                    .createQuery("From Student").list();
            transaction.commit();
        } catch (HibernateException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [{}] ", students.size());
        return students;
    }

    @Override
    public Student add(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (HibernateException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [{}] ", student);
        return student;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.delete(student);
            transaction.commit();
        } catch (HibernateException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [true] ");
        return true;
    }

    @Override
    public Student update(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
            session.close();
        } catch (HibernateException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [{}] ", student);
        return student;
    }

}
