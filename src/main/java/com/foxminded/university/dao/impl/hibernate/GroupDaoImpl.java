package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Group_;

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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> root = criteriaQuery.from(Group.class);
        CriteriaQuery<Group> select = criteriaQuery.select(root);
        return entityManager.createQuery(select).getResultList();
    }

    @Override
    @Transactional
    public Group add(Group group) {
        entityManager.persist(group);
        entityManager.flush();
        return group;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Group> criteriaDelete = criteriaBuilder.createCriteriaDelete(Group.class);
        Root<Group> root = criteriaDelete.from(Group.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get(Group_.id), id));
        entityManager.createQuery(criteriaDelete).executeUpdate();
        return true;
    }

    @Override
    @Transactional
    public Group update(Group group) {
        group = entityManager.merge(group);
        entityManager.flush();
        return group;
    }

}
