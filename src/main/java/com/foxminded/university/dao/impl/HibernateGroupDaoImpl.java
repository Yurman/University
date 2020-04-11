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

import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.dao.HibernateSessionFactory;
import com.foxminded.university.domain.Group;
import com.foxminded.university.exception.QueryNotExecuteException;

@Repository
public class HibernateGroupDaoImpl implements GroupDao {

    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private static final Logger logger = LoggerFactory.getLogger(HibernateGroupDaoImpl.class);

    @Override
    public Group getById(int id) {
        Group group = null;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            group = session.get(Group.class, id);
            transaction.commit();
            session.close();
        } catch (HibernateException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [{}] ", group);
        return group;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Group> getAll() {
        List<Group> groups = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            groups = sessionFactory.openSession()
                    .createQuery("From Group").list();
            transaction.commit();
        } catch (HibernateException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [{}] ", groups.size());
        return groups;
    }

    @Override
    public Group add(Group group) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(group);
            transaction.commit();
        } catch (HibernateException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [{}] ", group);
        return group;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Group group = session.get(Group.class, id);
            session.delete(group);
            transaction.commit();
        } catch (HibernateException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [true] ");
        return true;
    }

    @Override
    public Group update(Group group) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(group);
            transaction.commit();
            session.close();
        } catch (HibernateException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [{}] ", group);
        return group;
    }

}
