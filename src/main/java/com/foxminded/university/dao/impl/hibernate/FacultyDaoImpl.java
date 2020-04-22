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

import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Faculty_;

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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Faculty> criteriaQuery = criteriaBuilder.createQuery(Faculty.class);
        Root<Faculty> root = criteriaQuery.from(Faculty.class);
        CriteriaQuery<Faculty> select = criteriaQuery.select(root);
        return entityManager.createQuery(select).getResultList();
    }

    @Override
    @Transactional
    public Faculty add(Faculty faculty) {
        entityManager.persist(faculty);
        entityManager.flush();
        return faculty;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Faculty> criteriaDelete = criteriaBuilder.createCriteriaDelete(Faculty.class);
        Root<Faculty> root = criteriaDelete.from(Faculty.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get(Faculty_.id), id));
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
