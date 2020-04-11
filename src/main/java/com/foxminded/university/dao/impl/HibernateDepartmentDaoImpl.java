package com.foxminded.university.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.dao.HibernateSessionFactory;
import com.foxminded.university.domain.Department;
import com.foxminded.university.exception.QueryNotExecuteException;

@Repository
public class HibernateDepartmentDaoImpl implements DepartmentDao {

    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public Department getById(int id) {
        Department department = null;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            department = session.get(Department.class, id);
            transaction.commit();
            session.close();
        } catch (HibernateException ex) {

        }
        return department;
    }

    @SuppressWarnings("unchecked")

    @Override
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            departments = sessionFactory.openSession()
                    .createQuery("From Department").list();
            transaction.commit();
        } catch (HibernateException e) {
            throw new QueryNotExecuteException();
        }
        return departments;
    }

    @Override
    public Department add(Department department) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
        } catch (HibernateException e) {
            throw new QueryNotExecuteException();
        }
        return department;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Department department = session.get(Department.class, id);
            session.delete(department);
            transaction.commit();
        } catch (HibernateException e) {
            throw new QueryNotExecuteException();
        }
        return true;
    }

    @Override
    public Department update(Department department) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(department);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new QueryNotExecuteException();
        }
        return department;
    }

}
