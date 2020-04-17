package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        return entityManager.createQuery("select a from Faculty a", Faculty.class).getResultList();
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
        Faculty faculty = entityManager.find(Faculty.class, id);
        entityManager.remove(faculty);
        return true;
    }

    @Override
    @Transactional
    public Faculty update(Faculty faculty) {
        faculty = entityManager.merge(faculty);
        entityManager.persist(faculty);
        return faculty;
    }

}
