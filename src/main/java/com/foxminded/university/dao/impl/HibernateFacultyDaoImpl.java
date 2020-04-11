package com.foxminded.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.dao.HibernateSessionFactory;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.exception.QueryNotExecuteException;

@Repository
public class HibernateFacultyDaoImpl implements FacultyDao {

    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public Faculty getById(int id) {
        Faculty faculty = null;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            faculty = session.get(Faculty.class, id);
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {

        }
        return faculty;
    }

    @SuppressWarnings("unchecked")

    @Override
    public List<Faculty> getAll() {
        List<Faculty> faculties = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            faculties = sessionFactory.openSession()
                    .createQuery("From Group").list();
            transaction.commit();
        } catch (HibernateException e) {
            throw new QueryNotExecuteException();
        }
        return faculties;
    }

    @Override
    public Faculty add(Faculty faculty) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(faculty);
            transaction.commit();
        } catch (HibernateException e) {
            throw new QueryNotExecuteException();
        }
        return faculty;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Faculty faculty = session.get(Faculty.class, id);
            session.delete(faculty);
            transaction.commit();
        } catch (HibernateException e) {
            throw new QueryNotExecuteException();
        }
        return true;
    }

    @Override
    public Faculty update(Faculty faculty) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(faculty);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new QueryNotExecuteException();
        }
        return faculty;
    }

}
