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

import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.domain.Faculty;

@Repository("facultyDaoHibernate")
@Primary
public class FacultyDaoImpl implements FacultyDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Faculty getById(int id) {
        return entityManager.find(Faculty.class, id);
    }

    @Override
    @Transactional
    public List<Faculty> getAll() {
        CriteriaQuery<Faculty> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Faculty.class);
        CriteriaQuery<Faculty> select = criteriaQuery.select(criteriaQuery.from(Faculty.class));
        TypedQuery<Faculty> typedQuery = entityManager.createQuery(select);

        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public Faculty add(Faculty faculty) {
        entityManager.persist(faculty);
        return faculty;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        CriteriaDelete<Faculty> criteriaDelete = entityManager.getCriteriaBuilder().createCriteriaDelete(Faculty.class);
        Root<Faculty> root = criteriaDelete.from(Faculty.class);
        criteriaDelete.where(root.get("id").in(id));
        entityManager.createQuery(criteriaDelete).executeUpdate();
        return true;
    }

    @Override
    @Transactional
    public Faculty update(Faculty faculty) {
        faculty = entityManager.merge(faculty);
        entityManager.flush();
        return faculty;
    }

}
