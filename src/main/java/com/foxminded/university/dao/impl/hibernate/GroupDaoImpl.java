package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.domain.Group;

@Repository("groupDaoHibernate")
@Primary
public class GroupDaoImpl implements GroupDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Group getById(int id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    @Transactional
    public List<Group> getAll() {
        return entityManager.createQuery("select a from Group a", Group.class).getResultList();
    }

    @Override
    @Transactional
    public Group add(Group group) {
        entityManager.persist(group);
        return group;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Group group = entityManager.find(Group.class, id);
        entityManager.remove(group);
        return true;
    }

    @Override
    @Transactional
    public Group update(Group group) {
        group = entityManager.merge(group);
        entityManager.persist(group);
        return group;
    }

}
