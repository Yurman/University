package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.domain.Group;

@Repository("groupDaoHibernate")
@Primary
public class GroupDaoImpl implements GroupDao {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("university").createEntityManager();

    @Override
    public Group getById(int id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public List<Group> getAll() {
        return entityManager.createQuery("select a from Group a", Group.class).getResultList();
    }

    @Override
    public Group add(Group group) {
        entityManager.getTransaction().begin();
        entityManager.persist(group);
        entityManager.getTransaction().commit();
        return group;
    }

    @Override
    public boolean delete(int id) {
        Group group = entityManager.find(Group.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(group);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public Group update(Group group) {
        entityManager.getTransaction().begin();
        entityManager.persist(group);
        entityManager.getTransaction().commit();
        return group;
    }

}
