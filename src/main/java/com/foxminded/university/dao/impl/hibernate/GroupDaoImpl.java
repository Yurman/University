package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        CriteriaQuery<Group> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Group.class);
        CriteriaQuery<Group> select = criteriaQuery.select(criteriaQuery.from(Group.class));
        TypedQuery<Group> typedQuery = entityManager.createQuery(select);

        return typedQuery.getResultList();
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
        CriteriaDelete<Group> criteriaDelete = entityManager.getCriteriaBuilder().createCriteriaDelete(Group.class);
        Root<Group> root = criteriaDelete.from(Group.class);
        criteriaDelete.where(root.get("id").in(id));
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
