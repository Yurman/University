package com.foxminded.university.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.HibernateSessionFactory;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;

@Repository
public class HibernateStudentDaoImpl implements StudentDao {

    SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    Session session;

    @Override
    public Student getById(int id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, id);
        transaction.commit();
        session.close();
        return student;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getAll() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Student> students = sessionFactory.openSession()
                .createQuery("From Student").list();
        transaction.commit();
        session.close();

        return students;
    }

    @Override
    public Student add(Student student) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public boolean delete(int id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.delete(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student update(Student student) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
        return student;
    }

}
