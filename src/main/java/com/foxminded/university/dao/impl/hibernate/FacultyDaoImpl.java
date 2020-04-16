package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.domain.Faculty;

@Repository("facultyDaoHibernate")
@Primary
public class FacultyDaoImpl implements FacultyDao {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("university").createEntityManager();

    @Override
    public Faculty getById(int id) {
        return entityManager.find(Faculty.class, id);
    }

    @Override
    public List<Faculty> getAll() {
        return entityManager.createQuery("select a from Group a", Faculty.class).getResultList();
    }

    @Override
    public Faculty add(Faculty faculty) {
        entityManager.getTransaction().begin();
        entityManager.persist(faculty);
        entityManager.getTransaction().commit();
        return faculty;
    }

    @Override
    public boolean delete(int id) {
        Faculty faculty = entityManager.find(Faculty.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(faculty);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public Faculty update(Faculty faculty) {
        entityManager.getTransaction().begin();
        entityManager.persist(faculty);
        entityManager.getTransaction().commit();
        return faculty;
    }

}
